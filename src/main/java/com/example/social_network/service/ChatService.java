package com.example.social_network.service;

import com.example.social_network.dto.request.ChatRequest;
import com.example.social_network.dto.request.CreateChatRequest;
import com.example.social_network.dto.response.ChatResponse;
import com.example.social_network.entity.Chat;
import com.example.social_network.entity.User;

import java.util.List;

public interface ChatService {
    public ChatResponse createChat(User reqUser, User user2, CreateChatRequest request);

    public ChatResponse findChatById(Integer chatId);

    public List<ChatResponse> findUserChat(Integer userId);

    public List<ChatResponse> findAllChat();
}
