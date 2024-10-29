package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.CommentRequest;
import com.example.social_network.dto.response.CommentResponse;
import com.example.social_network.entity.Comment;
import com.example.social_network.entity.Post;
import com.example.social_network.entity.Reels;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.CommentMapper;
import com.example.social_network.repository.CommentRepository;
import com.example.social_network.repository.PostRepository;
import com.example.social_network.repository.ReelsRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    PostRepository postRepository;

    UserRepository userRepository;

    ReelsRepository reelsRepository;

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
    public CommentResponse createCommentReel(CommentRequest request, Integer reelId, Integer userId) {
        Reels reel = reelsRepository.findById(reelId).orElseThrow(()-> new AppException(ErrorCode.REEL_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Comment comment = commentMapper.toComment(request);

        comment.setCreateAt(LocalDateTime.now());

        comment.setUser(user);

        Comment saveComment = commentRepository.saveAndFlush(comment);

        reel.getComments().add(saveComment);

        reelsRepository.saveAndFlush(reel);

        return commentMapper.toCommentResponse(saveComment);
    }

    @Override
    public CommentResponse updateComment(CommentRequest request, Integer postId, Integer userId, Integer commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if(request.getContent() != null){
            comment.setContent(request.getContent());
        }
        comment = commentRepository.saveAndFlush(comment);
        return commentMapper.toCommentResponse(comment);
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

    @Override
    public List<CommentResponse> findAllComment() {
        return commentRepository.findAll().stream().map(commentMapper::toCommentResponse).toList();
    }

    @Override
    public CommentResponse findCommentByPost(Integer commentId, Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        return commentMapper.toCommentResponse(comment);
    }


    @Override
    public void deleteComment(Integer commentId, Integer userId, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AppException(ErrorCode.DELETE_POST);
        }

        post.getComments().remove(comment);
        postRepository.save(post);

        commentRepository.delete(comment);
    }
}
