package com.fsk.rabbitmqtutorial.exchange.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfiguration {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("exchange.direct.test");
    }

    @Bean
    public Queue directQueue() {
        return new Queue("queue.direct.test");
    }

    @Bean
    public Binding bindingDirect(Queue directQueue, DirectExchange directExchange) {
        // Routing Key "kirmizi" ise bu kuyruğa git
        return BindingBuilder.bind(directQueue).to(directExchange).with("kirmizi");
    }
}

