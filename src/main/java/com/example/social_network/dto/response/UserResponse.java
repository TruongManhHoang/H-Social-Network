package com.example.social_network.dto.response;

import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Post;
import com.example.social_network.entity.User;
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
    String password;
    LocalDate dob;
    String gender;
    List<Integer> followers = new ArrayList<>();
    List<Integer> followings = new ArrayList<>();
    List<Post> savedPosts = new ArrayList<>();
    List<Chat> chats = new ArrayList<>();

    public UserResponse(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.dob = user.getDob();
        this.gender = user.getGender();
        this.followers = user.getFollowers();
        this.followings = user.getFollowings();
        this.savedPosts = user.getSavedPosts();
        this.chats = user.getChats();
    }


}
