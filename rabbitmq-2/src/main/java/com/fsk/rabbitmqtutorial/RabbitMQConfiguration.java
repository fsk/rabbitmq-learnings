package com.fsk.rabbitmqtutorial;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(QueueConstants.EXCHANGE_NAME);
    }

    @Bean
    public Declarables smsDeclarables() {
        Queue smsQueue = new Queue(QueueConstants.SMS_QUEUE, true, false, false);
        return new Declarables(
                smsQueue,
                BindingBuilder.bind(smsQueue).to(directExchange()).with(QueueConstants.SMS_ROUTING_KEY)
        );
    }

    @Bean
    public Declarables emailDeclarables() {
        Queue emailQueue = new Queue(QueueConstants.EMAIL_QUEUE, true, false, false);
        return new Declarables(
                emailQueue,
                BindingBuilder.bind(emailQueue).to(directExchange()).with(QueueConstants.EMAIL_ROUTING_KEY)
        );
    }

    @Bean
    public Declarables slackDeclarables() {
        Queue slackQueue = new Queue(QueueConstants.SLACK_QUEUE, true, false, false);
        return new Declarables(
                slackQueue,
                BindingBuilder.bind(slackQueue).to(directExchange()).with(QueueConstants.SLACK_ROUTING_KEY)
        );
    }
}
