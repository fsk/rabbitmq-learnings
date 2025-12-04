package com.fsk.rabbitmqtutorial.exchange.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * Topic Exchange için mesaj gönderen örnek Producer sınıfı
 * 
 * Topic Exchange'de routing key pattern matching kullanılır:
 * - * (yıldız): Tek bir kelime yerine geçer
 * - # (hash): Sıfır veya daha fazla kelime yerine geçer
 * 
 * Bu örnekte "spor.#" pattern'i kullanılıyor.
 */
@Component
public class TopicExchangeProducer {

    private final RabbitTemplate rabbitTemplate;
    private static final String EXCHANGE_NAME = "exchange.topic.test";

    public TopicExchangeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * "spor.futbol" routing key ile mesaj gönderir (spor.# pattern'ine uyar)
     */
    public void sendFootballNews(String news) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "spor.futbol", news);
        System.out.println("Sent to Topic Exchange [Routing Key: spor.futbol]: " + news);
    }

    /**
     * "spor.basketbol" routing key ile mesaj gönderir (spor.# pattern'ine uyar)
     */
    public void sendBasketballNews(String news) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "spor.basketbol", news);
        System.out.println("Sent to Topic Exchange [Routing Key: spor.basketbol]: " + news);
    }

    /**
     * "spor.tenis" routing key ile mesaj gönderir (spor.# pattern'ine uyar)
     */
    public void sendTennisNews(String news) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "spor.tenis", news);
        System.out.println("Sent to Topic Exchange [Routing Key: spor.tenis]: " + news);
    }

    /**
     * "teknoloji.haber" routing key ile mesaj gönderir (spor.# pattern'ine UYMAZ, mesaj kaybolur)
     */
    public void sendTechNews(String news) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "teknoloji.haber", news);
        System.out.println("Sent to Topic Exchange [Routing Key: teknoloji.haber]: " + news);
        System.out.println("⚠️ This message will be lost - pattern doesn't match!");
    }

    /**
     * Örnek kullanım: Farklı spor haberleri gönderme
     */
    public void sendExampleMessages() {
        sendFootballNews("Topic Message: Goal scored! Fenerbahçe 1-0");
        sendBasketballNews("Topic Message: Basketball match started");
        sendTennisNews("Topic Message: Tennis tournament continues");
        sendTechNews("Topic Message: New technology news (this message will be lost)");
    }
}

