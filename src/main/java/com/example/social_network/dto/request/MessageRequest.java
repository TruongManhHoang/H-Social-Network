package com.example.social_network.dto.request;

import com.example.social_network.entity.Chat;
import com.example.social_network.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MessageRequest {

    String content;

    String image;

    LocalDateTime timestamp;

    User user;

    Chat chat;
}
