package com.example.social_network.dto.request;

import com.example.social_network.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateChatRequest {

    private Integer userId;
}
