package com.example.social_network.controller;

import com.example.social_network.dto.request.StoryRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.StoryResponse;
import com.example.social_network.entity.Story;
import com.example.social_network.entity.User;
import com.example.social_network.service.StoryService;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StoryController {

    StoryService storyService;

    UserService userService;

    @PostMapping
    public ApiResponse<StoryResponse> createStory(@RequestBody StoryRequest storyRequest, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<StoryResponse>builder()
                .result(storyService.createStory(storyRequest, user))
                .build();
    }
    @GetMapping("/user")
    public ApiResponse<List<StoryResponse>> findStoryByUser(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<List<StoryResponse>>builder()
                .result(storyService.findStoryByUserId(user.getId()))
                .build();
    }

    @GetMapping
    public ApiResponse<List<StoryResponse>> findAllStory(){
        return ApiResponse.<List<StoryResponse>>builder()
                .result(storyService.findAll())
                .build();
    }
}
