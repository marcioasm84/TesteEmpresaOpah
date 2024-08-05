package com.testeopah.TesteOpah.document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Carrinho {
	@Id
	private String id;
    private Map<String, Integer> produtos; // Mapeia Produto para a quantidade
    private Date dataCriacao;
    private Date dataAlteracao;

    public Carrinho() {
    	/*
        this.produtos = new HashMap<>();
        this.dataCriacao = new Date();
        this.dataAlteracao = new Date();*/
    }

    public Map<String, Integer> getProdutos() {
        return produtos;
    }

    public void setProdutos(Map<String, Integer> produtos) {
        this.produtos = produtos;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void adicionarProduto(String produto, int quantidade) {
        produtos.put(produto, quantidade);
        this.dataAlteracao = new Date();
    }

    public void removerProduto(Produto produto) {
        produtos.remove(produto);
        this.dataAlteracao = new Date();
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
