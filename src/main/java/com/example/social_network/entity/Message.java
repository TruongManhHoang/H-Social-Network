package com.example.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String content;

    String image;

    LocalDateTime timestamp;

    @ManyToOne
    @JsonIgnore
    User user;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    Chat chat;



}
