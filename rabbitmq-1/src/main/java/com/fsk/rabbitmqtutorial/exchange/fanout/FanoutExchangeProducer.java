package com.fsk.rabbitmqtutorial.exchange.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Fanout Exchange için mesaj gönderen örnek Producer sınıfı
 * 
 * Fanout Exchange'de routing key önemsizdir.
 * Mesaj, exchange'e bağlı TÜM kuyruklara gönderilir.
 * Bu örnekte 2 kuyruk var: queue.fanout.test.1 ve queue.fanout.test.2
 */
@Component
public class FanoutExchangeProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "exchange.fanout.test";

    public FanoutExchangeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Fanout Exchange'e mesaj gönderir (routing key önemsiz, boş string kullanılabilir)
     */
    public void sendBroadcastMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", message);
        System.out.println("Sent to Fanout Exchange (All queues): " + message);
    }

    /**
     * Örnek: Duyuru mesajı gönderme
     */
    public void sendAnnouncement(String announcement) {
        sendBroadcastMessage("ANNOUNCEMENT: " + announcement);
    }

    /**
     * Örnek kullanım: Farklı türde broadcast mesajları
     */
    public void sendExampleMessages() {
        sendBroadcastMessage("Fanout Message: Broadcast to Everyone!");
        sendAnnouncement("System maintenance will start at 23:00");
        sendBroadcastMessage("New feature added: Notification system");
    }
}

