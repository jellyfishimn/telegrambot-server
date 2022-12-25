package org.asap.telegrambot_server.telegram;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.service.RabbitMQProducer;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBotInitializer {

    private final RabbitMQProducer rabbitMQProducer;

    @PostConstruct
    public void test() {
        try {
            var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBotHandler(rabbitMQProducer));
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }
}
