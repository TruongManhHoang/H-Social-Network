package com.example.social_network.controller;

import com.example.social_network.entity.Message;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RealTimeChat {
    SimpMessagingTemplate simpMessage;

    @MessageMapping("/chat/{groupId}")
    public Message sendToUser(@Payload Message message, @DestinationVariable String groupId){
        simpMessage.convertAndSendToUser(groupId, "/private", message);
        return message;
    }
}
