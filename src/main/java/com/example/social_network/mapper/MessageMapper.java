package com.example.social_network.mapper;

import com.example.social_network.dto.request.MessageRequest;
import com.example.social_network.dto.response.MessageResponse;
import com.example.social_network.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mapping(target = "chat", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    Message toMessage(MessageRequest request);

    MessageResponse toMessageResponse(Message message);
}
