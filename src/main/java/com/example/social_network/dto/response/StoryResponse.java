package com.example.social_network.dto.response;

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
public class StoryResponse {
    Integer id;
    User user;

    String image;

    String caption;

    LocalDateTime createAt;
}
