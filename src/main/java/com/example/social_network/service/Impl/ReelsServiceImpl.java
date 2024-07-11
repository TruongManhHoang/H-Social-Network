package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.ReelsRequest;
import com.example.social_network.dto.response.ReelsResponse;
import com.example.social_network.entity.Reels;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.ReelsMapper;
import com.example.social_network.repository.ReelsRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.ReelsService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReelsServiceImpl implements ReelsService {

    ReelsRepository reelsRepository;

    UserRepository userRepository;

    ReelsMapper reelsMapper;

    @Override
    public ReelsResponse createReel(ReelsRequest request, User user) {
        Reels reels = reelsMapper.toReels(request);
        reels.setUser(user);
        return reelsMapper.toReelResponse(reelsRepository.saveAndFlush(reels));
    }

    @Override
    public List<ReelsResponse> findAllReels() {
        return reelsRepository.findAll().stream().map(reelsMapper::toReelResponse).toList();
    }

    @Override
    public List<ReelsResponse> findUserReel(Integer userId) {
        if(userRepository.findById(userId).isEmpty()) throw new AppException(ErrorCode.USER_NOT_FOUND);
        return reelsRepository.findByUserId(userId).stream().map(reelsMapper::toReelResponse).toList();
    }
}
