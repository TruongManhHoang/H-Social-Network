package com.example.social_network.service;

import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.dto.response.PostResponse;

import java.util.List;

public interface PostService {
    public PostResponse createPost(PostRequest request,Integer userId);

    public void delete (Integer postId, Integer userId);

    public List<PostResponse> findPostByUserId(Integer userId);

    public PostResponse findPostById(Integer id);

    public List<PostResponse> findAllPost();

    public PostResponse savePost(Integer postId, Integer userId);

    public PostResponse likePost(Integer postId, Integer userId);
}
