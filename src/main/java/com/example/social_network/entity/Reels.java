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
@Table(name = "reels")
public class Reels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String title;

    String video;

    @ManyToOne
    @JsonIgnore
    User user;

    @ManyToMany
    @JsonIgnore
    List<User> liked = new ArrayList<>();

    LocalDateTime createdAt;

    @OneToMany
    @JsonIgnore
    List<Comment> comments = new ArrayList<>();

}
