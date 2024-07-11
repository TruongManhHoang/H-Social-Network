package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.CommentRequest;
import com.example.social_network.dto.response.CommentResponse;
import com.example.social_network.entity.Comment;
import com.example.social_network.entity.Post;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.CommentMapper;
import com.example.social_network.repository.CommentRepository;
import com.example.social_network.repository.PostRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    PostRepository postRepository;

    UserRepository userRepository;

    CommentMapper commentMapper;
    @Override
    public CommentResponse createComment(CommentRequest request, Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentMapper.toComment(request);
        comment.setCreateAt(LocalDateTime.now());
        comment.setUser(user);
        Comment savedComment = commentRepository.saveAndFlush(comment);

        post.getComments().add(savedComment);

        postRepository.saveAndFlush(post);
        return commentMapper.toCommentResponse(savedComment);
    }

    @Override
    public CommentResponse likeComment(Integer commentId, Integer userId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!comment.getLiked().contains(user)){
            comment.getLiked().add(user);
        }else {
            comment.getLiked().remove(user);
        }

        return commentMapper.toCommentResponse(commentRepository.saveAndFlush(comment));
    }

    @Override
    public CommentResponse findCommentById(Integer CommentId) {
        Optional<Comment> comment = commentRepository.findById(CommentId);
        if (comment.isEmpty()){
            throw new AppException(ErrorCode.COMMENT_NOT_FOUND);
        }

        return commentMapper.toCommentResponse(comment.get());
    }
}
