package com.fsk.rabbitmqtutorial.exchange.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Fanout Exchange için mesaj dinleyen örnek Consumer sınıfları
 * 
 * Fanout Exchange'de aynı mesaj tüm kuyruklara gider.
 * Bu örnekte 2 farklı consumer, 2 farklı kuyruktan mesaj dinliyor.
 */
@Component
public class FanoutExchangeConsumer {

    /**
     * İlk kuyruktan mesaj dinler
     */
    @RabbitListener(queues = "queue.fanout.test.1")
    public void receiveFromQueue1(String message) {
        System.out.println("=== Message Received from Fanout Queue 1 ===");
        System.out.println("Message: " + message);
        System.out.println("Queue: queue.fanout.test.1");
        System.out.println("================================");
    }

    /**
     * İkinci kuyruktan mesaj dinler
     */
    @RabbitListener(queues = "queue.fanout.test.2")
    public void receiveFromQueue2(String message) {
        System.out.println("=== Message Received from Fanout Queue 2 ===");
        System.out.println("Message: " + message);
        System.out.println("Queue: queue.fanout.test.2");
        System.out.println("================================");
    }

    /**
     * Örnek: Farklı işlemler için ayrı consumer'lar
     * Queue 1: Email gönderme servisi
     */
    @RabbitListener(queues = "queue.fanout.test.1")
    public void processEmailNotification(String message) {
        System.out.println("📧 Sending Email: " + message);
        // Email gönderme mantığı buraya yazılabilir
    }

    /**
     * Queue 2: SMS gönderme servisi
     */
    @RabbitListener(queues = "queue.fanout.test.2")
    public void processSmsNotification(String message) {
        System.out.println("📱 Sending SMS: " + message);
        // SMS gönderme mantığı buraya yazılabilir
    }
}

