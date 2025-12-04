package com.fsk.rabbitmqtutorial.exchange.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfiguration {

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("exchange.topic.test");
    }

    @Bean
    public Queue topicQueue() {
        return new Queue("queue.topic.test");
    }

    @Bean
    public Binding bindingTopic(Queue topicQueue, TopicExchange topicExchange) {
        // "spor." ile başlayan her şeyi yakala (örn: spor.futbol, spor.basketbol)
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("spor.#");
    }
}

