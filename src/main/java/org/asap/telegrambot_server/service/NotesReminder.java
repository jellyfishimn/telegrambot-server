package org.asap.telegrambot_server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.api.TelegramBotClient;
import org.asap.telegrambot_server.api.TelegramBotService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotesReminder {

    private final TelegramBotService telegramBotService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void remindUserToWalk() {

    }
}
