package com.testeopah.TesteOpah.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.testeopah.TesteOpah.document.Produto;

import reactor.core.publisher.Flux;

public interface ProdutoRepository extends ReactiveMongoRepository<Produto, String> {

	Flux<Produto> findAllByCategoriaNome(String nomeCategoria);

}

