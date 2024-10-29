package com.example.social_network.service.Impl;

import com.cloudinary.Cloudinary;
import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.dto.response.PostResponse;
import com.example.social_network.entity.Post;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.PostMapper;
import com.example.social_network.repository.PostRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.PostService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {
    PostRepository postRepository;

    UserRepository userRepository;

    PostMapper postMapper;

    Cloudinary cloudinary;

    @Override
    public PostResponse createPost(PostRequest request, Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        Post post = postMapper.toPost(request);

        post.setCreatedAt(LocalDateTime.now());
        post.setUser(user);
        return postMapper.toPostResponse(postRepository.saveAndFlush(post));
    }

    @Override
    public void delete(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if(!post.getUser().getId().equals(user.getId())){
            throw new AppException(ErrorCode.DELETE_POST);
        }
        for(User u : post.getUsers()) {
            u.getSavedPosts().remove(post);
            userRepository.save(u);
        }
        postRepository.delete(post);
    }

    @Override
    public List<PostResponse> findPostByUserId(Integer userId) {
//        List<Post> posts = postRepository.findPostByUserId(userId);
        return postRepository.findPostByUserId(userId).stream().map(postMapper::toPostResponse).toList();
    }

    @Override
    public PostResponse findPostById(Integer id) {
        return postMapper.toPostResponse(postRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND)));
    }

    @Override
    public PostResponse updatePost(PostRequest request, Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if(request.getUser() != null){
            post.setUser(request.getUser());
        }
        if(request.getCaption() != null){
            post.setCaption(request.getCaption());
        }
        if(request.getImage() != null){
            post.setImage(request.getImage());
        }
        if(request.getVideo() != null){
            post.setVideo(request.getVideo());
        }
        post = postRepository.saveAndFlush(post);
        return postMapper.toPostResponse(post);
    }

    @Override
    public List<PostResponse> findAllPost() {
        return postRepository.findAll().stream().map(postMapper::toPostResponse).toList();
    }

    @Transactional
    @Override
    public PostResponse savePost(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        } else {
            user.getSavedPosts().add(post);
        }

        // Lưu các thay đổi vào database
        userRepository.saveAndFlush(user);

        return postMapper.toPostResponse(post);
    }

    @Override
    public PostResponse likePost(Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new AppException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }else {
            post.getLiked().add(user);
        }

        return postMapper.toPostResponse(postRepository.saveAndFlush(post));
    }
}
