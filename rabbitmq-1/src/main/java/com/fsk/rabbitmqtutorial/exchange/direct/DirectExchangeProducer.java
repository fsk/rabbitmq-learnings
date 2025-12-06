package com.fsk.rabbitmqtutorial.exchange.direct;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Direct Exchange için mesaj gönderen örnek Producer sınıfı
 * Direct Exchange'de mesajlar routing key'e göre eşleşen kuyruğa yönlendirilir.
 */
@Component
public class DirectExchangeProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "exchange.direct.test";

    public DirectExchangeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * "kirmizi" routing key ile mesaj gönderir
     */
    public void sendRedMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "kirmizi", message, m -> {
            m.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            return m;
        });
        System.out.println("Sent to Direct Exchange [Routing Key: kirmizi]: " + message);
    }


}

