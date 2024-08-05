package com.testeopah.TesteOpah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testeopah.TesteOpah.document.Carrinho;
import com.testeopah.TesteOpah.dtos.CarrinhoDto;
import com.testeopah.TesteOpah.services.CarrinhoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/salvar")
    public Carrinho criarCarrinhoS(@RequestBody Carrinho carrinho) {
        return carrinho;
    }
    
    @PostMapping
    public Mono<Carrinho> criarCarrinho(@RequestBody Carrinho carrinho) {
        return carrinhoService.criarCarrinho(carrinho);
    }
    
    @DeleteMapping("/{id}")
    public Mono<Void> excluirCarrinho(@PathVariable String id) {
        return carrinhoService.excluirCarrinho(id);
    }
    
    @PutMapping("/{idCarrinho}/produtos/{idProduto}/{qtdProduto}")
    public Mono<Carrinho> incluirProdutoAoCarrinho(@PathVariable String idCarrinho, @PathVariable String idProduto, @PathVariable Integer qtdProduto) {
        return carrinhoService.incluirProdutoAoCarrinho(idCarrinho, idProduto, qtdProduto);
    }
    
    @DeleteMapping("/{idCarrinho}/produtos/{idProduto}")
    public Mono<Carrinho> excluirProdutoAoCarrinho(@PathVariable String idCarrinho, @PathVariable String idProduto) {
        return carrinhoService.excluirProdutoAoCarrinho(idCarrinho, idProduto);
    }
}
