package com.example.social_network.service;

import com.example.social_network.dto.request.ReelsRequest;
import com.example.social_network.dto.response.ReelsResponse;
import com.example.social_network.entity.Reels;
import com.example.social_network.entity.User;

import java.util.List;

public interface ReelsService {

    public ReelsResponse createReel(ReelsRequest request, User user);

    public List<ReelsResponse> findAllReels();

    List<ReelsResponse> findUserReel(Integer userId);

    public void deleteReel(Integer reelId, Integer userId);

    public ReelsResponse likeReel(Integer reelId, Integer userId);
}
