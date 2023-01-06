package org.asap.telegrambot_server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.api.TelegramBotClient;
import org.asap.telegrambot_server.model.dto.TelegramParseTextMode;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/telegram-service")
@RestController
@RequiredArgsConstructor
public class TelegramBotController {

    private final TelegramBotClient client;

    @PostMapping("/sendMessage")
    public void test(@RequestParam final String chatId, @RequestParam final String message) {
        client.sendMessage(chatId, message, TelegramParseTextMode.MarkdownV2);
    }
}
