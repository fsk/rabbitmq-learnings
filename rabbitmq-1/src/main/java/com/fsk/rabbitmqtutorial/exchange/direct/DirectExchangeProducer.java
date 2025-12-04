package com.fsk.rabbitmqtutorial.exchange.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Direct Exchange için mesaj gönderen örnek Producer sınıfı
 * 
 * Direct Exchange'de mesajlar routing key'e göre eşleşen kuyruğa yönlendirilir.
 * Bu örnekte "kirmizi" routing key'i kullanılıyor.
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
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "kirmizi", message);
        System.out.println("Sent to Direct Exchange [Routing Key: kirmizi]: " + message);
    }

    /**
     * "mavi" routing key ile mesaj gönderir (bu routing key'e bağlı kuyruk yok, mesaj kaybolur)
     */
    public void sendBlueMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "mavi", message);
        System.out.println("Sent to Direct Exchange [Routing Key: mavi]: " + message);
    }

    /**
     * Örnek kullanım: Farklı routing key'ler ile mesaj gönderme
     */
    public void sendExampleMessages() {
        sendRedMessage("Direct Message: Red Alert!");
        sendBlueMessage("Direct Message: Blue Warning! (This message will be lost - routing key doesn't match)");
    }
}

