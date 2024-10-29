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

import java.util.List;

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

    @PostMapping("/reel/{reelId}")
    public ApiResponse<CommentResponse> createCommentReel(@RequestBody CommentRequest request, @PathVariable("reelId") Integer reelId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<CommentResponse>builder()
                .result(commentService.createCommentReel(request,reelId,user.getId()))
                .build();
    }

    @PutMapping("/update/{commentId}/post/{postId}")
    public ApiResponse<CommentResponse> updateComment(@PathVariable("commentId") Integer commentId, @PathVariable("postId") Integer postId, @RequestHeader("Authorization") String jwt, @RequestBody CommentRequest request){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<CommentResponse>builder()
                .result(commentService.updateComment(request,postId,user.getId(),commentId))
                .build();
    }

    @PutMapping("/like/{commentId}")
    public ApiResponse<CommentResponse> likeComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<CommentResponse>builder()
                .result(commentService.likeComment(commentId, user.getId()))
                .build();
    }
    @GetMapping()
    public ApiResponse<List<CommentResponse>> getComment(){
        return ApiResponse.<List<CommentResponse>>builder()
                .result(commentService.findAllComment())
                .build();
    }

    @DeleteMapping("/{commentId}/post/{postId}")
    public ApiResponse<String> deleteComment(@PathVariable Integer commentId, @RequestHeader("Authorization") String jwt, @PathVariable("postId") Integer postId){
        User user = userService.findUserByJwt(jwt);
        commentService.deleteComment(commentId,user.getId(),postId);
        return ApiResponse.<String>builder()
                .result("Comment has been delete")
                .build();
    }


}
