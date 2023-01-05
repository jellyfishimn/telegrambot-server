package org.asap.telegrambot_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.model.entity.Message;
import org.asap.telegrambot_server.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate template;

    private final MessageRepository messageRepository;

    public void produce(final Message message) {
        template.convertAndSend("notes-exchange", "notes-key", message.getMessage());
        log.debug("Produced message to notes-server");

        messageRepository.save(message);
    }
}
