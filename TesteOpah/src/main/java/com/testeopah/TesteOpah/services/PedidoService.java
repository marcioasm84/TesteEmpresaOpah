package com.testeopah.TesteOpah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeopah.TesteOpah.document.Pedido;
import com.testeopah.TesteOpah.document.Pedido.Status;
import com.testeopah.TesteOpah.publishers.NovoPedidoEventPublisher;
import com.testeopah.TesteOpah.repository.CarrinhoRepository;
import com.testeopah.TesteOpah.repository.PedidoRepository;
import com.testeopah.TesteOpah.repository.ProdutoRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private NovoPedidoEventPublisher novoPedidoEventPublisher;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Mono<Pedido> buscarPedidoPorIdCarrinho(String idCarrinho) {
        return pedidoRepository.findByCarrinhoId(idCarrinho);
    }
    
    public Mono<Pedido> criarPedido(String idCarrinho) {
    	Mono<Pedido> pedidoMono = carrinhoRepository.findById(idCarrinho)
			    	.flatMap(carrinho -> {
			    		
			    		var pedido = new Pedido(carrinho, Status.WAIT_PAYMENT);
			            return pedidoRepository.save(pedido);
			            
			        }).flatMap(savedPedido -> {
			        	
			        	 var atualizacaoProdutos = Flux.fromIterable(savedPedido.getCarrinho().getProdutos().entrySet())
		                            .flatMap(entry -> {
		                                String idProduto = entry.getKey();
		                                Integer quantidade = entry.getValue();
		                                return produtoRepository.findById(idProduto)
		                                    .flatMap(produto -> {
		                                        produto.diminueQuantidade(quantidade);
		                                        return produtoRepository.save(produto);
		                                    });
		                            })
		                            .then(Mono.defer(() -> {
		                                // Publica evento após todas as atualizações dos produtos serem salvas
		                                System.out.println("*** PUBLICANDO O PEDIDO = " + savedPedido.getId());
		                                novoPedidoEventPublisher.publishNovoPedidoEvent(savedPedido.getId());
		                                return Mono.just(savedPedido);
		                            }));
			        	 
			        	 return atualizacaoProdutos;
			        });
    	return pedidoMono;
    }

	public void efetivarPagamento(String idPedido) {
		System.out.println("Efetivando o pagamento");
		pedidoRepository.findById(idPedido)
						.flatMap(pedido -> {
							pedido.setStatus(Pedido.gerarStatus()); //Gera aleatoriamente o status
							return pedidoRepository.save(pedido).flatMap(savedPedido -> {
								
								if(savedPedido.getStatus().equals(Status.DONE)) {
									System.out.println("Pagamento com sucesso - foi DONE");
									return Mono.just(savedPedido);
								}
								
								//Atualiza a quantidade original
								var atualizacaoProdutos = Flux.fromIterable(savedPedido.getCarrinho().getProdutos().entrySet())
								.flatMap(entry -> {
								    String idProduto = entry.getKey();
								    Integer quantidade = entry.getValue();
								    return produtoRepository.findById(idProduto)
								        .flatMap(produto -> {
								        	System.out.println("Devolveu a quantidade porque o status foi ERROR_PAYMENT");
								            produto.aumentaQuantidade(quantidade);
								            return produtoRepository.save(produto);
								        });
								})
								.then(Mono.defer(() -> {
								    return Mono.just(savedPedido);
								}));
		 
								 return atualizacaoProdutos;
							});
						}).subscribe();
	}
}
