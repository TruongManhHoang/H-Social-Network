package com.example.social_network.mapper;

import com.example.social_network.dto.request.StoryRequest;
import com.example.social_network.dto.response.StoryResponse;
import com.example.social_network.entity.Story;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StoryMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Story toStory(StoryRequest storyRequest);

    StoryResponse toStoryResponse(Story story);
}
