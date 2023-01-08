package org.asap.telegrambot_server.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.api.TelegramBotClient;
import org.asap.telegrambot_server.api.TelegramBotService;
import org.asap.telegrambot_server.model.dto.TelegramParseTextMode;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBotServiceImpl implements TelegramBotService {

    private final TelegramBotClient client;

    @Override
    public void sendMessage(final Long chatId, final String message) {
        client.sendMessage(String.valueOf(chatId), message, TelegramParseTextMode.MarkdownV2);
    }
}
