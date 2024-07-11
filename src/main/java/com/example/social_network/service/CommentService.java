package com.example.social_network.service;

import com.example.social_network.dto.request.CommentRequest;
import com.example.social_network.dto.response.CommentResponse;

public interface CommentService {

    public CommentResponse createComment(CommentRequest request, Integer postId, Integer userId);

    public CommentResponse likeComment(Integer commentId, Integer userId);

    public CommentResponse findCommentById(Integer CommentId);

}
