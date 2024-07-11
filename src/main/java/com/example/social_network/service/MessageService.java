package com.example.social_network.service;

import com.example.social_network.dto.request.MessageRequest;
import com.example.social_network.dto.response.MessageResponse;
import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Message;
import com.example.social_network.entity.User;

import java.util.List;

public interface MessageService {
    public MessageResponse createMessage(User user, Integer chatId, MessageRequest request);

    public List<MessageResponse> findChatMessage(Integer chatId);


}
