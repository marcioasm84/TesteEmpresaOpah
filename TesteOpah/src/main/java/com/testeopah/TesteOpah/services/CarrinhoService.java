package com.testeopah.TesteOpah.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testeopah.TesteOpah.document.Carrinho;
import com.testeopah.TesteOpah.repository.CarrinhoRepository;
import com.testeopah.TesteOpah.repository.ProdutoRepository;

import reactor.core.publisher.Mono;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public Mono<Carrinho> criarCarrinho(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }
    
    public Mono<Void> excluirCarrinho(String id) {
        return carrinhoRepository.deleteById(id);
    }
    
    public Mono<Carrinho> incluirProdutoAoCarrinho(String idCarrinho, String idProduto, Integer quantidade) {
        return produtoRepository.findById(idProduto)
                .flatMap(produto -> carrinhoRepository.findById(idCarrinho)
                        .flatMap(carrinho -> {
                        	int qtd = 0;
                        	var produtoSalvo = carrinho.getProdutos().get(produto.getId());
                        	if(produtoSalvo != null) {
                        		qtd = produtoSalvo.intValue();
                        	}
                            carrinho.getProdutos().put(produto.getId(), quantidade + qtd);
                            return carrinhoRepository.save(carrinho);
                        }));
    }
    
    public Mono<Carrinho> excluirProdutoAoCarrinho(String idCarrinho, String idProduto) {
        return produtoRepository.findById(idProduto)
                .flatMap(produto -> carrinhoRepository.findById(idCarrinho)
                        .flatMap(carrinho -> {
                            carrinho.getProdutos().remove(produto.getId());
                            return carrinhoRepository.save(carrinho);
                        }));
    }
}

