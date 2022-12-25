package org.asap.telegrambot_server.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() {
        var cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("user");
        cachingConnectionFactory.setPassword("password");
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("notes-exchange", true, false);
    }
    @Bean
    public Queue queue() {
        return new Queue("notes-queue");
    }

    @Bean
    public Binding binding(final DirectExchange directExchange, final Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with("notes-key");
    }
}
