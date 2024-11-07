package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.CreateChatRequest;
import com.example.social_network.dto.response.ChatResponse;
import com.example.social_network.entity.Chat;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.ChatMapper;
import com.example.social_network.repository.ChatRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.ChatService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatServiceImpl implements ChatService {

    ChatRepository chatRepository;

    ChatMapper chatMapper;

    UserRepository userRepository;

    @Override
    public ChatResponse createChat(User reqUser, User user2, CreateChatRequest request) {
        Chat isExist = chatRepository.findChatByUsers(user2, reqUser);
        if (isExist != null) {
            return chatMapper.toChatResponse(isExist);
        }

        Chat chat = new Chat();
        chat.setChatName(request.getChatName());
        chat.getUsers().add(user2);
        chat.getUsers().add(reqUser);
        chat.setTimestamp(LocalDateTime.now());

        Chat savedChat = chatRepository.saveAndFlush(chat);
        return chatMapper.toChatResponse(savedChat);
    }

    @Override
    public ChatResponse findChatById(Integer chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new AppException(ErrorCode.CHAT_NOT_FOUND));
        return chatMapper.toChatResponse(chat);
    }

    @Override
    public List<ChatResponse> findUserChat(Integer userId) {
        return chatRepository.findByUsers_Id(userId).stream().map(chatMapper::toChatResponse).toList();
    }

    @Override
    public List<ChatResponse> findAllChat() {
        return chatRepository.findAll().stream().map(chatMapper::toChatResponse).toList();
    }
}
