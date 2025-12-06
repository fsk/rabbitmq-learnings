package com.fsk.rabbitmqtutorial.controller;

import com.fsk.rabbitmqtutorial.exchange.direct.DirectExchangeProducer;
import com.fsk.rabbitmqtutorial.exchange.fanout.FanoutExchangeProducer;
import com.fsk.rabbitmqtutorial.exchange.headers.HeadersExchangeProducer;
import com.fsk.rabbitmqtutorial.exchange.topic.TopicExchangeProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * Sadece Direct Exchange'e mesaj gönderir
     */
    @PostMapping("/direct")
    public String sendDirect(@RequestParam String message) {
        directProducer.sendRedMessage(message);
        return "Message sent to Direct Exchange!";
    }

    /**
     * Sadece Fanout Exchange'e mesaj gönderir
     */
    @PostMapping("/fanout")
    public String sendFanout(@RequestParam String message) {
        fanoutProducer.sendBroadcastMessage(message);
        return "Message sent to Fanout Exchange!";
    }

    /**
     * Sadece Topic Exchange'e mesaj gönderir
     */
    @PostMapping("/topic")
    public String sendTopic(@RequestParam String message) {
        topicProducer.sendFootballNews(message);
        return "Message sent to Topic Exchange!";
    }

    /**
     * Sadece Headers Exchange'e mesaj gönderir
     */
    @PostMapping("/headers")
    public String sendHeaders(@RequestParam String message) {
        headersProducer.sendPdfHighSecurityMessage(message);
        return "Message sent to Headers Exchange!";
    }
}

