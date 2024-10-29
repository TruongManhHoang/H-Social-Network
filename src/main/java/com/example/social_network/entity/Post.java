package com.example.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String caption;

    String image;

    String video;

    LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    User user;

    @ManyToMany
    @JsonIgnore
    List<User> liked = new ArrayList<>();

    @ManyToMany(mappedBy = "savedPosts")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @OneToMany
    @JsonIgnore
    List<Comment> comments = new ArrayList<>();

}
