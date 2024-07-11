package com.example.social_network.dto.request;

import com.example.social_network.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReelsRequest {

    String title;

    String video;

    User user;

}
