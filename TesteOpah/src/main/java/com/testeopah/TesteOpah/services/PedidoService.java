package com.testeopah.TesteOpah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeopah.TesteOpah.document.Pedido;
import com.testeopah.TesteOpah.document.Pedido.Status;
import com.testeopah.TesteOpah.publishers.NovoPedidoEventPublisher;
import com.testeopah.TesteOpah.repository.CarrinhoRepository;
import com.testeopah.TesteOpah.repository.PedidoRepository;

import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private NovoPedidoEventPublisher novoPedidoEventPublisher;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    public Mono<Pedido> buscarPedidoPorIdCarrinho(String idCarrinho) {
        return pedidoRepository.findByCarrinhoId(idCarrinho);
    }
    
    public Mono<Pedido> criarPedido(String idCarrinho) {
    	Mono<Pedido> pedidoMono = carrinhoRepository.findById(idCarrinho)
			    	.flatMap(carrinho -> {
			    		var pedido = new Pedido(carrinho, Status.WAIT_PAYMENT);
			            return pedidoRepository.save(pedido);
			        }).doOnSuccess(result -> {
			        	System.out.print("***PUBLICANDO O PEDIDO = " + result.getId());
		        		novoPedidoEventPublisher.publishNovoPedidoEvent(result.getId());
			        });
    	return pedidoMono;
    }

	public void efetivarPagamento(String idPedido) {
		System.out.println("Efetivando o pagamento");
		pedidoRepository.findById(idPedido)
						.flatMap(pedido -> {
							pedido.setStatus(Pedido.gerarStatus()); //Gera aleatoriamente o status
							return pedidoRepository.save(pedido);
						}).subscribe();
	}
}
