package org.asap.telegrambot_server.api;

import org.asap.telegrambot_server.model.dto.TelegramMessage;
import org.asap.telegrambot_server.model.dto.TelegramParseTextMode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "telegram.bot",
        contextId = "telegram.bot-client",
        url = "${telegram.bot.url}" + "${telegram.bot.token}",
        configuration = FeignConfiguration.class)
public interface TelegramBotClient {

    @PostMapping("/sendMessage")
    TelegramMessage sendMessage(@RequestParam("chat_id") String chatId,
                                @RequestParam("text") String message,
                                @RequestParam("parse_mode") TelegramParseTextMode mode);
}
