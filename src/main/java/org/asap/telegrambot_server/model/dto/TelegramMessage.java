package org.asap.telegrambot_server.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelegramMessage {

    @JsonProperty("message_id")
    private String messageId;

    private Integer date;

    private String text;
}
