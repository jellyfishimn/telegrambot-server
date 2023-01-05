package org.asap.telegrambot_server.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class Message extends BaseEntity {

    private String username;

    private LocalDateTime time;

    private String message;
}
