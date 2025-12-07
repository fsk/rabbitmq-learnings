package com.fsk.rabbitmqtutorial;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationListenerService {


    @RabbitListener(queues = QueueConstants.SMS_QUEUE)
    public void smsListener(Message message) {

        byte[] body = message.getBody();
        String bodyString = new String(body, StandardCharsets.UTF_8);

        System.out.println(bodyString);

        message.getMessageProperties().getHeaders().forEach((key, value) -> {
            System.out.println("key: " + key + " value: " + value);
        });

    }

    @RabbitListener(queues = QueueConstants.SLACK_QUEUE)
    public void slackListener(Message message) {

        byte[] body = message.getBody();
        String bodyString = new String(body, StandardCharsets.UTF_8);

        System.out.println(bodyString);

        message.getMessageProperties().getHeaders().forEach((key, value) -> {
            System.out.println("key: " + key + " value: " + value);
        });

    }

    @RabbitListener(queues = QueueConstants.EMAIL_QUEUE)
    public void emailListener(Message message) {

        byte[] body = message.getBody();
        String bodyString = new String(body, StandardCharsets.UTF_8);

        System.out.println(bodyString);

        message.getMessageProperties().getHeaders().forEach((key, value) -> {
            System.out.println("key: " + key + " value: " + value);
        });

    }

}
