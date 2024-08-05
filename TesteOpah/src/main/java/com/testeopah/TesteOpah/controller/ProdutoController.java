package com.testeopah.TesteOpah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testeopah.TesteOpah.document.Produto;
import com.testeopah.TesteOpah.services.ProdutoService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/categoria")
    public Flux<Produto> buscarProdutosPorCategoria(@RequestParam String nomeCategoria) {
        return produtoService.buscarProdutosPorCategoria(nomeCategoria);
    }
    
    @GetMapping("/all")
    public Flux<Produto> buscarProdutos() {
        return produtoService.buscarProdutos();
    }
}

