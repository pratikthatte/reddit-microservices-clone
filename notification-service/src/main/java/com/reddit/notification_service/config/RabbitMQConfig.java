package com.reddit.notification_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
	public static final String NOTIFICATION_QUEUE = "notification-queue";
    public static final String NOTIFICATION_EXCHANGE = "notification-exchange";
    
    @Bean
    public Queue queue() {
    	return new Queue(NOTIFICATION_QUEUE,true);
    }
    @Bean
    public DirectExchange exchange() {
    	return new DirectExchange(NOTIFICATION_EXCHANGE);
    }
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(NOTIFICATION_QUEUE);
    }
}
