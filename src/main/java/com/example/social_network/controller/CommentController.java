package com.example.social_network.controller;

import com.example.social_network.dto.request.CommentRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.CommentResponse;
import com.example.social_network.entity.User;
import com.example.social_network.service.CommentService;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {

    CommentService commentService;

    UserService userService;

    @PostMapping("/post/{postId}")
    public ApiResponse<CommentResponse> createComment(@RequestBody CommentRequest request, @PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<CommentResponse>builder()
                .result(commentService.createComment(request,postId,user.getId()))
                .build();
    }

    @PutMapping("/like/{commentId}")
    public ApiResponse<CommentResponse> likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<CommentResponse>builder()
                .result(commentService.likeComment(commentId, user.getId()))
                .build();
    }
    @GetMapping("/{commentId}")
    public ApiResponse<CommentResponse> getComment(@PathVariable Integer commentId){
        return ApiResponse.<CommentResponse>builder()
                .result(commentService.findCommentById(commentId))
                .build();
    }
}
