package org.asap.telegrambot_server.telegram;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.asap.telegrambot_server.service.RabbitMQProducer;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.asap.telegrambot_server.Constants.TELEGRAM_BOT_NAME;
import static org.asap.telegrambot_server.Constants.TELEGRAM_BOT_TOKEN;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramBotHandler extends TelegramLongPollingBot {

    private final RabbitMQProducer producer;

    @Override
    public String getBotUsername() {
        return TELEGRAM_BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return TELEGRAM_BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage()) {
            var message = update.getMessage();
            if (message.hasText()) {
                var sendMessageRequest = new SendMessage();
                sendMessageRequest.setChatId(message.getChatId().toString());
                sendMessageRequest.setText("Message: " + message.getText());
                try {
                    producer.produce(message.getText());
                    execute(sendMessageRequest);
                } catch (TelegramApiException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }
}
