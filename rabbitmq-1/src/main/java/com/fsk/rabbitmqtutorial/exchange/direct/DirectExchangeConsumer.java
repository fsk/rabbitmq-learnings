package com.fsk.rabbitmqtutorial.exchange.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Direct Exchange için mesaj dinleyen örnek Consumer sınıfı
 * 
 * Bu consumer sadece "queue.direct.test" kuyruğundan mesajları dinler.
 * Direct Exchange'de routing key tam eşleşme gerektirir.
 */
@Component
public class DirectExchangeConsumer {

    @RabbitListener(queues = "queue.direct.test")
    public void receiveMessage(String message) {
        System.out.println("Message received from Direct Exchange: " + message);
        System.out.println("Routing Key: kirmizi matched message");
    }

    /**
     * Örnek: Mesaj işleme mantığı
     */
    @RabbitListener(queues = "queue.direct.test")
    public void processDirectMessage(String message) {
        System.out.println("=== Processing Direct Exchange Message ===");
        System.out.println("Message: " + message);
        System.out.println("Queue: queue.direct.test");
        System.out.println("Exchange: exchange.direct.test");
        System.out.println("Routing Key: kirmizi");
        System.out.println("================================");
    }
}

