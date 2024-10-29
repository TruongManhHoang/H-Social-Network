package com.example.social_network.mapper;

import com.example.social_network.dto.request.ReelsRequest;
import com.example.social_network.dto.response.ReelsResponse;
import com.example.social_network.entity.Reels;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReelsMapper {
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Reels toReels(ReelsRequest request);

    ReelsResponse toReelResponse(Reels reels);
}
