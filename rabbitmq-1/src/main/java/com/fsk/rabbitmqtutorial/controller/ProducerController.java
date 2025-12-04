package com.fsk.rabbitmqtutorial.controller;

import com.fsk.rabbitmqtutorial.exchange.direct.DirectExchangeProducer;
import com.fsk.rabbitmqtutorial.exchange.fanout.FanoutExchangeProducer;
import com.fsk.rabbitmqtutorial.exchange.headers.HeadersExchangeProducer;
import com.fsk.rabbitmqtutorial.exchange.topic.TopicExchangeProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Tüm Exchange türlerine mesaj gönderen örnek Controller
 * 
 * Bu controller, her exchange türü için oluşturulan Producer sınıflarını kullanır.
 */
@RestController
@RequestMapping("/api/send")
@RequiredArgsConstructor
public class ProducerController {

    private final DirectExchangeProducer directProducer;
    private final FanoutExchangeProducer fanoutProducer;
    private final TopicExchangeProducer topicProducer;
    private final HeadersExchangeProducer headersProducer;


    /**
     * Tüm exchange türlerine örnek mesajlar gönderir
     */
    @GetMapping("/all")
    public String sendToAllExchanges() {
        directProducer.sendExampleMessages();
        fanoutProducer.sendExampleMessages();
        topicProducer.sendExampleMessages();
        headersProducer.sendExampleMessages();
        
        return "Messages successfully sent to 4 different Exchange types!";
    }

    /**
     * Sadece Direct Exchange'e mesaj gönderir
     */
    @GetMapping("/direct")
    public String sendDirect() {
        directProducer.sendExampleMessages();
        return "Messages sent to Direct Exchange!";
    }

    /**
     * Sadece Fanout Exchange'e mesaj gönderir
     */
    @GetMapping("/fanout")
    public String sendFanout() {
        fanoutProducer.sendExampleMessages();
        return "Messages sent to Fanout Exchange!";
    }

    /**
     * Sadece Topic Exchange'e mesaj gönderir
     */
    @GetMapping("/topic")
    public String sendTopic() {
        topicProducer.sendExampleMessages();
        return "Messages sent to Topic Exchange!";
    }

    /**
     * Sadece Headers Exchange'e mesaj gönderir
     */
    @GetMapping("/headers")
    public String sendHeaders() {
        headersProducer.sendExampleMessages();
        return "Messages sent to Headers Exchange!";
    }
}

