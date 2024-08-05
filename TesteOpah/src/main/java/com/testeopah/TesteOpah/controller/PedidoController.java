package com.testeopah.TesteOpah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testeopah.TesteOpah.document.Pedido;
import com.testeopah.TesteOpah.dtos.PedidoDto;
import com.testeopah.TesteOpah.services.PedidoService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/carrinho")
    public Mono<Pedido> buscarPedidoPorIdCarrinho(@RequestParam String idCarrinho) {
        return pedidoService.buscarPedidoPorIdCarrinho(idCarrinho);
    }
    
    @PostMapping
    public Mono<Pedido> criarPedido(@RequestBody PedidoDto pedidoDto) {
        return pedidoService.criarPedido(pedidoDto.getIdCarrinho());
    }

}
