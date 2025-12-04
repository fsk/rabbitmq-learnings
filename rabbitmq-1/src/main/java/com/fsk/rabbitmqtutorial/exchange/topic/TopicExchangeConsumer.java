package com.fsk.rabbitmqtutorial.exchange.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Topic Exchange için mesaj dinleyen örnek Consumer sınıfı
 * 
 * Topic Exchange'de routing key pattern matching kullanılır.
 * Bu consumer "spor.#" pattern'ine uyan tüm mesajları alır:
 * - spor.futbol ✓
 * - spor.basketbol ✓
 * - spor.tenis ✓
 * - spor.futbol.maç ✓
 * - teknoloji.haber ✗ (eşleşmez)
 */
@Component
public class TopicExchangeConsumer {

    /**
     * "spor.#" pattern'ine uyan tüm mesajları dinler
     */
    @RabbitListener(queues = "queue.topic.test")
    public void receiveSportsNews(String message) {
        System.out.println("=== Sports News Received from Topic Exchange ===");
        System.out.println("Message: " + message);
        System.out.println("Queue: queue.topic.test");
        System.out.println("Exchange: exchange.topic.test");
        System.out.println("Pattern: spor.#");
        System.out.println("================================");
    }

    /**
     * Örnek: Spor haberlerini kategorize etme
     */
    @RabbitListener(queues = "queue.topic.test")
    public void processSportsNews(String message) {
        if (message.contains("futbol")) {
            System.out.println("⚽ Processing Football News: " + message);
        } else if (message.contains("basketbol")) {
            System.out.println("🏀 Processing Basketball News: " + message);
        } else if (message.contains("tenis")) {
            System.out.println("🎾 Processing Tennis News: " + message);
        } else {
            System.out.println("📰 Processing General Sports News: " + message);
        }
    }
}

