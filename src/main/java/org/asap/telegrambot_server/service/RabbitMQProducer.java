package org.asap.telegrambot_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate template;

    public void produce(final String message) {
        template.convertAndSend("notes-exchange", "notes-key", message);
        log.debug("Produced message to notes-server");
    }
}
