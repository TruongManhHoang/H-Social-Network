package com.example.social_network.dto.response;

import com.example.social_network.entity.Comment;
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
public class PostResponse {
    Integer id;

    String caption;

    String image;

    String video;

    LocalDateTime createdAt;

    User user;

    List<User> liked;

    private List<User> users = new ArrayList<>();

    List<Comment> comments = new ArrayList<>();
}
