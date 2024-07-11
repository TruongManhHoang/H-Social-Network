package com.example.social_network.mapper;

import com.example.social_network.dto.request.ChatRequest;
import com.example.social_network.dto.request.StoryRequest;
import com.example.social_network.dto.response.ChatResponse;
import com.example.social_network.dto.response.StoryResponse;
import com.example.social_network.entity.Chat;
import com.example.social_network.entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    Chat toChat(ChatRequest request);

    ChatResponse toChatResponse(Chat chat);
}
