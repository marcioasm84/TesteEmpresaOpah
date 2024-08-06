package com.testeopah.TesteOpah.publishers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NovoPedidoEventPublisher {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Value(value = "${app.broker.exchange.novoPedidoEvent}")
	private String exchangeNovoPedidoEvent;
	
	public void publishNovoPedidoEvent(String p) {
		rabbitTemplate.convertAndSend(exchangeNovoPedidoEvent, "", p);
	}
}
