package com.testeopah.TesteOpah.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.testeopah.TesteOpah.document.Carrinho;

import reactor.core.publisher.Mono;

public interface CarrinhoRepository extends ReactiveMongoRepository<Carrinho, String> {

}
