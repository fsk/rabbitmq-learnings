package com.fsk.rabbitmqtutorial.exchange.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfiguration {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("exchange.fanout.test");
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("queue.fanout.test.1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("queue.fanout.test.2");
    }

    @Bean
    public Binding bindingFanout1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    @Bean
    public Binding bindingFanout2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}

