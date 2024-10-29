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
public class ReelsResponse {

    Integer id;

    String title;

    String video;
    
    User user;

    List<User> liked;

    List<CommentResponse> comments;

    LocalDateTime createdAt;
}
