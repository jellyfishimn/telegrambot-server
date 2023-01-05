package org.asap.telegrambot_server.telegram;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class TelegramBotHandler extends TelegramLongPollingBot {

    private final String botToken;
    private final String botName;
    private final RabbitMQProducer producer;

    public TelegramBotHandler(@Value("${telegram.bot.token}") final String botToken,
                              @Value("${telegram.bot.name}") final String botName,
                              final RabbitMQProducer producer) {
        this.botToken = botToken;
        this.botName = botName;
        this.producer = producer;
    }

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
