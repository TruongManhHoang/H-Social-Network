package com.example.social_network.service;

import com.example.social_network.dto.request.StoryRequest;
import com.example.social_network.dto.response.StoryResponse;
import com.example.social_network.entity.Story;
import com.example.social_network.entity.User;

import java.util.List;

public interface StoryService {

    public StoryResponse createStory(StoryRequest request, User user);

    public List<StoryResponse> findStoryByUserId(Integer userId);

    public List<StoryResponse> findAll();
}
