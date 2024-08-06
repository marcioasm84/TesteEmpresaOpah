package com.testeopah.TesteOpah.consumers;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.testeopah.TesteOpah.services.PedidoService;

@Component
public class NovoPedidoConsumer {

	@Autowired
	PedidoService pedidoService;
	
	@RabbitListener(
			bindings = @QueueBinding(
						value = @Queue(value = "${app.broker.queue.novoPedidoEventQueue.name}", durable = "true"),
						exchange = @Exchange(value="${app.broker.exchange.novoPedidoEvent}", type = ExchangeTypes.FANOUT, ignoreDeclarationExceptions = "true")
			)
	)
	public void listenNovoPedidoEvent(@Payload String idPedido) {
		pedidoService.efetivarPagamento(idPedido);
		System.out.println("Novo pedido");

	}
}
