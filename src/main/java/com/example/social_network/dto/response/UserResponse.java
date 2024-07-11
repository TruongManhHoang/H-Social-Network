package com.example.social_network.dto.response;

import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Post;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Integer id;
    String firstName;
    String lastName;
    String email;
    LocalDate dob;
    String gender;
    List<Integer> followers = new ArrayList<>();
    List<Integer> followings = new ArrayList<>();
    List<Post> savedPosts = new ArrayList<>();
    List<Chat> chats = new ArrayList<>();
}
