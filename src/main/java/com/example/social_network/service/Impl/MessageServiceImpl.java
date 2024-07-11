package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.MessageRequest;
import com.example.social_network.dto.response.MessageResponse;
import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Message;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.MessageMapper;
import com.example.social_network.repository.ChatRepository;
import com.example.social_network.repository.MessageRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.ChatService;
import com.example.social_network.service.MessageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl implements MessageService {

    MessageRepository messageRepository;

    MessageMapper messageMapper;

    ChatRepository chatRepository;

    UserRepository userRepository;

    @Override
    public MessageResponse createMessage(User user, Integer chatId, MessageRequest request) {

        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new AppException(ErrorCode.CHAT_NOT_FOUND));

        Message message = messageMapper.toMessage(request);
        message.setTimestamp(LocalDateTime.now());
        message.setUser(user);
        message.setChat(chat);

        Message savedMessage = messageRepository.saveAndFlush(message);

        chat.getMessages().add(savedMessage);
        chatRepository.saveAndFlush(chat);

        return messageMapper.toMessageResponse(savedMessage);
    }

    @Override
    public List<MessageResponse> findChatMessage(Integer chatId) {

        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new AppException(ErrorCode.CHAT_NOT_FOUND));

        return messageRepository.findByChatId(chatId).stream().map(messageMapper::toMessageResponse).toList();
    }

}
