package com.testeopah.TesteOpah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeopah.TesteOpah.document.Produto;
import com.testeopah.TesteOpah.repository.ProdutoRepository;

import reactor.core.publisher.Flux;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Flux<Produto> buscarProdutosPorCategoria(String nomeCategoria) {
        return produtoRepository.findAllByCategoriaNome(nomeCategoria);
    }
    
    public Flux<Produto> buscarProdutos() {
        return produtoRepository.findAll();
    }
}