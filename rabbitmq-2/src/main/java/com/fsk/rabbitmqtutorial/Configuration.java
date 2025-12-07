//package com.fsk.rabbitmqtutorial;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfiguration {
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange(QueueConstants.EXCHANGE_NAME);
//    }
//
//
//    @Bean(name = "smsQueue")
//    public Queue smsQueue() {
//        return new Queue(QueueConstants.SMS_QUEUE, true,  false, false);
//    }
//
//    @Bean(name = "emailQueue")
//    public Queue emailQueue() {
//        return new Queue(QueueConstants.EMAIL_QUEUE, true,  false, false);
//    }
//
//    @Bean(name = "slackQueue")
//    public Queue slackQueue() {
//        return new Queue(QueueConstants.SLACK_QUEUE, true,  false, false);
//    }
//
//
//    @Bean(name = "bindingSmsQueue")
//    public Binding bindingSmsQueue() {
//        return BindingBuilder.bind(smsQueue()).to(directExchange()).with(QueueConstants.SMS_QUEUE);
//    }
//
//    @Bean(name = "bindingEmailQueue")
//    public Binding bindingEmailQueue() {
//        return BindingBuilder.bind(emailQueue()).to(directExchange()).with(QueueConstants.EMAIL_QUEUE);
//    }
//
//    @Bean(name = "bindingSlackQueue")
//    public Binding bindingSlackQueue() {
//        return BindingBuilder.bind(slackQueue()).to(directExchange()).with(QueueConstants.SLACK_QUEUE);
//    }
//
//
//}
