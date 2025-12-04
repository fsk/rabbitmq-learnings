package com.fsk.rabbitmqtutorial.exchange.headers;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HeadersExchangeConfiguration {

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange("exchange.headers.test");
    }

    @Bean
    public Queue headersQueue() {
        return new Queue("queue.headers.test");
    }

    @Bean
    public Binding bindingHeaders(Queue headersQueue, HeadersExchange headersExchange) {
        Map<String, Object> headerValues = new HashMap<>();
        headerValues.put("dosya-tipi", "pdf");
        headerValues.put("gizlilik", "yüksek");

        // Header'da hem dosya-tipi=pdf HEM DE gizlilik=yüksek varsa eşleşir
        return BindingBuilder.bind(headersQueue).to(headersExchange).whereAll(headerValues).match();
    }
}

