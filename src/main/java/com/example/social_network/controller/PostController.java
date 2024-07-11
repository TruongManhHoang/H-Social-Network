package com.example.social_network.controller;

import com.example.social_network.dto.request.PostRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.PostResponse;
import com.example.social_network.entity.User;
import com.example.social_network.service.PostService;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    UserService userService;

    @PostMapping()
    public ApiResponse<PostResponse> createPost(@RequestBody PostRequest request,@RequestHeader("Authorization") String jwt ) {
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<PostResponse>builder()
                .result(postService.createPost(request, user.getId()))
                .build();
    }

    @DeleteMapping("/{postId}")
    public ApiResponse<String> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);
        postService.delete(postId, user.getId());
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }
    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> findPostById(@PathVariable Integer postId){
        return ApiResponse.<PostResponse>builder()
                .result(postService.findPostById(postId))
                .build();
    }

    @GetMapping("/user")
    public ApiResponse<List<PostResponse>> findUserPostId(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.findPostByUserId(user.getId()))
                .build();
    }
    @PutMapping("/save/{postId}")
    public ApiResponse<PostResponse> updatePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<PostResponse>builder()
                .result(postService.savePost(postId, user.getId()))
                .build();
    }

    @GetMapping()
    public ApiResponse<List<PostResponse>> findAllPost(){
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.findAllPost())
                .build();
    }

    @PutMapping("/like/{postId}")
    public ApiResponse<PostResponse> likePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<PostResponse>builder()
                .result(postService.likePost(postId, user.getId()))
                .build();
    }

}