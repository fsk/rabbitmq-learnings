package com.fsk.rabbitmqtutorial.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consume")
@RequiredArgsConstructor
public class ConsumerController {

    private final RabbitTemplate rabbitTemplate;

    private ResponseEntity<?> readMessage(String queueName) {
        Message message = rabbitTemplate.receive(queueName);
        return (message != null) ? ResponseEntity.ok(new String(message.getBody())) : ResponseEntity.noContent().build();
    }

    @GetMapping("/direct")
    public ResponseEntity<?> consumeDirect() {
        return readMessage("queue.direct.test");
    }

    @GetMapping("/fanout/1")
    public ResponseEntity<?> consumeFanout1() {
        return readMessage("queue.fanout.test.1");
    }

    @GetMapping("/fanout/2")
    public ResponseEntity<?> consumeFanout2() {
        return readMessage("queue.fanout.test.2");
    }

    @GetMapping("/topic")
    public ResponseEntity<?> consumeTopic() {
        return readMessage("queue.topic.test");
    }

    @GetMapping("/headers")
    public ResponseEntity<?> consumeHeaders() {
        return readMessage("queue.headers.test");
    }
}
