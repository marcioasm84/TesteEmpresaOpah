package com.testeopah.TesteOpah.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.testeopah.TesteOpah.document.Pedido;

import reactor.core.publisher.Mono;

public interface PedidoRepository extends ReactiveMongoRepository<Pedido, String> {

	Mono<Pedido> findByCarrinhoId(String idCarrinho);
	
}

