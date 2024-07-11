package com.example.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String firstName;
    String lastName;
    String email;
    String password;
    LocalDate dob;
    String gender;
    List<Integer> followers = new ArrayList<>();
    List<Integer> followings = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_save_post",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    List<Post> savedPosts = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    List<Chat> chats = new ArrayList<>();
}
