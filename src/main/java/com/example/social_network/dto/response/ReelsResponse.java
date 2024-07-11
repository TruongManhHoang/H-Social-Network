package com.example.social_network.dto.response;

import com.example.social_network.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReelsResponse {

    Long id;

    String title;

    String video;
    
    User user;

}
