package com.testeopah.TesteOpah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeopah.TesteOpah.document.Pedido;
import com.testeopah.TesteOpah.document.Pedido.Status;
import com.testeopah.TesteOpah.repository.CarrinhoRepository;
import com.testeopah.TesteOpah.repository.PedidoRepository;

import reactor.core.publisher.Mono;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    public Mono<Pedido> buscarPedidoPorIdCarrinho(String idCarrinho) {
        return pedidoRepository.findByCarrinhoId(idCarrinho);
    }
    
    public Mono<Pedido> criarPedido(String idCarrinho) {
    	return carrinhoRepository.findById(idCarrinho)
		    	.flatMap(carrinho -> {
		    		var pedido = new Pedido(carrinho, Status.WAIT_PAYMENT);
		            return pedidoRepository.save(pedido);
		        });
    }
}
