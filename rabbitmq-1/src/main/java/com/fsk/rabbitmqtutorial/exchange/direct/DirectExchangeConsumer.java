package com.fsk.rabbitmqtutorial.exchange.direct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * Direct Exchange için mesaj dinleyen örnek Consumer sınıfı
 * 
 * Bu consumer sadece "queue.direct.test" kuyruğundan mesajları dinler.
 * Direct Exchange'de routing key tam eşleşme gerektirir.
 * 
 * NOT: @Component annotation'ı kaldırıldı - Artık otomatik çalışmıyor.
 * Mesajlar sadece ConsumerController üzerinden manuel olarak okunuyor.
 */
public class DirectExchangeConsumer {

    @RabbitListener(queues = "queue.direct.test")
    public void receiveMessage(String message) {
        System.out.println("=== Message Received from Direct Exchange ===");
        System.out.println("Message: " + message);
        System.out.println("Queue: queue.direct.test");
        System.out.println("Exchange: exchange.direct.test");
        System.out.println("Routing Key: kirmizi");
        System.out.println("================================");
    }
}
