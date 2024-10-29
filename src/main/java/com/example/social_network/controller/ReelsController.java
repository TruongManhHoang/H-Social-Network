package com.example.social_network.controller;

import com.example.social_network.dto.request.ReelsRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.ReelsResponse;
import com.example.social_network.entity.Reels;
import com.example.social_network.entity.User;
import com.example.social_network.service.ReelsService;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/reels")
public class ReelsController {

    ReelsService reelsService;

    UserService userService;

    @PostMapping()
    ApiResponse<ReelsResponse> createReels(@RequestBody ReelsRequest request, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<ReelsResponse>builder()
                .result(reelsService.createReel(request,user))
                .build();
    }

    @GetMapping()
    ApiResponse<List<ReelsResponse>> findAllReels() {
        return ApiResponse.<List<ReelsResponse>>builder()
                .result(reelsService.findAllReels())
                .build();
    }

    @GetMapping("/user")
    ApiResponse<List<ReelsResponse>> findUserReels(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<List<ReelsResponse>>builder()
                .result(reelsService.findUserReel(user.getId()))
                .build();
    }

    @DeleteMapping("/{reelId}")
    public ApiResponse<String> deleteReel(@PathVariable Integer reelId,@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        reelsService.deleteReel(reelId,user.getId());
        return ApiResponse.<String>builder()
                .result("Reel has been deleted")
                .build();
    }

    @PutMapping("/like/{reelId}")
    public ApiResponse<ReelsResponse> likeReel(@PathVariable Integer reelId,@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<ReelsResponse>builder()
                .result(reelsService.likeReel(reelId, user.getId()))
                .build();
    }
}
