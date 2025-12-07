package com.fsk.rabbitmqtutorial;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducerService {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotificationToQueue(String routingKey, String message) {
        rabbitTemplate.convertAndSend(QueueConstants.EXCHANGE_NAME, routingKey, message);
        log.info("Send notification message to queue: {}", routingKey);
    }

}
