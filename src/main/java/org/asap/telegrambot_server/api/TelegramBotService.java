package org.asap.telegrambot_server.api;

import org.asap.telegrambot_server.model.entity.Message;

public interface TelegramBotService {

    void sendMessage(Long chatId, String message);
}
