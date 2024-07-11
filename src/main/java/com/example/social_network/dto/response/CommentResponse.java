package com.example.social_network.dto.response;

import com.example.social_network.entity.User;
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
public class CommentResponse {
    Integer id;

    String content;

    User user;

    List<User> liked = new ArrayList<>();

    LocalDateTime createAt;
}
