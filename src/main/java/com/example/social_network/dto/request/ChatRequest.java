package com.example.social_network.dto.request;

import com.example.social_network.entity.User;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRequest {

    String chatName;

    String chatImage;

    List<User> users = new ArrayList<>();

    LocalDateTime timestamp;
}