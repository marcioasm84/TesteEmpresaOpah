package com.testeopah.TesteOpah.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.testeopah.TesteOpah.document.Categoria;

import reactor.core.publisher.Mono;

public interface CategoriaRepository extends ReactiveMongoRepository<Categoria, String> {

}

