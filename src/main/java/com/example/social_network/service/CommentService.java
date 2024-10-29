package com.example.social_network.service;

import com.example.social_network.dto.request.CommentRequest;
import com.example.social_network.dto.response.CommentResponse;

import java.util.List;

public interface CommentService {

    public CommentResponse createComment(CommentRequest request, Integer postId, Integer userId);
    public CommentResponse createCommentReel(CommentRequest request, Integer reelId, Integer userId);

    public CommentResponse updateComment(CommentRequest request, Integer postId, Integer userId, Integer commentId);

    public CommentResponse likeComment(Integer commentId, Integer userId);

    public CommentResponse findCommentById(Integer CommentId);

    public List<CommentResponse> findAllComment();

    public CommentResponse findCommentByPost(Integer commentId, Integer postId, Integer userId);

    public void deleteComment(Integer commentId, Integer userId, Integer postId);


}
