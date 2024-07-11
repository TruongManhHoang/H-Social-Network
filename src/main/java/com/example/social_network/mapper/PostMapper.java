package com.example.social_network.mapper;

import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.dto.response.PostResponse;
import com.example.social_network.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdAt", ignore = true)
    Post toPost(PostRequest postRequest);

    PostResponse toPostResponse(Post post);
}
