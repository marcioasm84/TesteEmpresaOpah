package com.testeopah.TesteOpah.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pedido {
    public enum Status {
        WAIT_PAYMENT, DONE, ERROR_PAYMENT
    }
    @Id
    private String id;
    private Carrinho carrinho;
    private Date dataCriacao;
    private Status status;

    public Pedido() {
    	
    }
    
    public Pedido(Carrinho carrinho, Status status) {
        this.carrinho = carrinho;
        this.dataCriacao = new Date();
        this.status = status;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
