package org.asap.telegrambot_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@Slf4j
@EnableRabbit
@EnableFeignClients
@SpringBootApplication
public class TelegramBotServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramBotServerApplication.class, args);
    }
}
