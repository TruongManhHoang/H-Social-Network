package com.example.social_network.service.Impl;

import com.example.social_network.dto.request.StoryRequest;
import com.example.social_network.dto.response.StoryResponse;
import com.example.social_network.entity.Story;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.StoryMapper;
import com.example.social_network.repository.StoryRepository;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.StoryService;
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
public class StoryServiceImpl implements StoryService {

    StoryRepository storyRepository;

    StoryMapper storyMapper;

    UserRepository userRepository;

    @Override
    public StoryResponse createStory(StoryRequest request, User user) {
        Story story = storyMapper.toStory(request);
        story.setCreateAt(LocalDateTime.now());
        story.setUser(user);
        return storyMapper.toStoryResponse(storyRepository.saveAndFlush(story));
    }

    @Override
    public List<StoryResponse> findStoryByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return storyRepository.findByUserId(userId).stream().map(storyMapper::toStoryResponse).toList();
    }

    @Override
    public List<StoryResponse> findAll() {
        return storyRepository.findAll().stream().map(storyMapper::toStoryResponse).toList();
    }
}
