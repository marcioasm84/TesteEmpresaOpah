package com.testeopah.TesteOpah;

import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.testeopah.TesteOpah.document.Categoria;
import com.testeopah.TesteOpah.document.Produto;
import com.testeopah.TesteOpah.repository.CategoriaRepository;
import com.testeopah.TesteOpah.repository.ProdutoRepository;

import reactor.core.publisher.Flux;
/*
@Component
public class CarregaDados implements CommandLineRunner {

	CategoriaRepository categoriaRepository;
	ProdutoRepository produtoRepository;
	
	CarregaDados(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
		this.categoriaRepository = categoriaRepository;
		this.produtoRepository = produtoRepository;
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		produtoRepository.deleteAll();
		categoriaRepository.deleteAll();
		
		Categoria c1 = new Categoria(UUID.randomUUID().toString(), "Eletronicos", "Produtos eletronicos");
		Categoria c2 = new Categoria(UUID.randomUUID().toString(), "Cozinha", "Produtos de cozinha");
		Categoria c3 = new Categoria(UUID.randomUUID().toString(), "Cama", "Produtos de cama, mesa e banho");
		
		Produto p1 = new Produto(UUID.randomUUID().toString(), "Smartphone", "Samsung Galaxy s22", 1, 100, c1);
		Produto p2 = new Produto(UUID.randomUUID().toString(), "Notebook", "Notebook Acer", 1, 2000, c1);
		
		Produto p3 = new Produto(UUID.randomUUID().toString(), "Jogo de copos", "Jogo de copos de vidro", 1, 30, c2);
		Produto p4 = new Produto(UUID.randomUUID().toString(), "Jogo de Talheres", "Jogo de Talheres tramontina", 1, 50, c2);
		
		Produto p5 = new Produto(UUID.randomUUID().toString(), "Jogo de lençol", "Jogo de lençol 180 fios", 1, 200, c3);
		Produto p6 = new Produto(UUID.randomUUID().toString(), "Edredom", "Edredom patchwork", 1, 250, c3);

		Flux.just(c1, c2, c3)
        .flatMap(categoriaRepository::save)
        .subscribe(System.out::println);
		
		Flux.just(p1, p2, p3, p4, p5, p6)
        .flatMap(produtoRepository::save)
        .subscribe(System.out::println);
        
		System.out.println("Dados carregados");
	}

}*/
