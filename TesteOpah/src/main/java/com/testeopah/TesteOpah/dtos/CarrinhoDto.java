package com.testeopah.TesteOpah.dtos;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;

public class CarrinhoDto {
	@Id
	private String id;
    private Map<String, Integer> produtos; // Mapeia Produto para a quantidade
    private Date dataCriacao;
    private Date dataAlteracao;

    public CarrinhoDto() {

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
