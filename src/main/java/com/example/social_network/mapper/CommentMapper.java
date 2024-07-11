package com.example.social_network.mapper;

import com.example.social_network.dto.request.CommentRequest;
import com.example.social_network.dto.response.CommentResponse;
import com.example.social_network.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "createAt", ignore = true)
    @Mapping(target = "user", ignore = true) // Ignoring user field
//    @Mapping(target = "post", ignore = true) // Ignoring post field
    Comment toComment(CommentRequest request);

    CommentResponse toCommentResponse(Comment comment);
}
