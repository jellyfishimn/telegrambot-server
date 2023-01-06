package org.asap.telegrambot_server.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.model.entity.Message;
import org.asap.telegrambot_server.service.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.time.ZoneId;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBotHandler extends TelegramLongPollingBot {

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.name}")
    private String botName;

    private final RabbitMQProducer producer;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage()) {
            var message = update.getMessage();
            if (message.hasText()) {
                var sendMessageRequest = new SendMessage();
                var username = message.getFrom().getUserName();
                // need to fix
                var time = Instant.ofEpochMilli(message.getDate()).atZone(ZoneId.systemDefault()).toLocalDateTime();
                var msg = Message.of(username, time, message.getText());

                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("Your message (reply):  " + message.getText() + "\nFrom - " + username);
                try {
                    producer.produce(msg);
                    execute(sendMessageRequest);
                } catch (TelegramApiException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
