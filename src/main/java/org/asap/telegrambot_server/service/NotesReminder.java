package org.asap.telegrambot_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.api.TelegramBotService;
import org.asap.telegrambot_server.repository.MessageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static org.asap.telegrambot_server.service.Messages.USER_WALK_MESSAGE;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotesReminder {

    private final TelegramBotService telegramBotService;

    private final MessageRepository messageRepository;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void remindUserToWalk() {
        messageRepository.getAllByChatIdIsNotNull().forEach(message -> {
            telegramBotService.sendMessage(message.getChatId(), USER_WALK_MESSAGE);
        });
    }
}
