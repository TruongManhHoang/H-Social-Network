package com.example.social_network.controller;

import com.example.social_network.dto.request.CreateChatRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.ChatResponse;
import com.example.social_network.entity.Chat;
import com.example.social_network.entity.User;
import com.example.social_network.service.ChatService;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatController {
    ChatService chatService;

    UserService userService;

    @PostMapping()
    public ApiResponse<ChatResponse> createChat(@RequestHeader("Authorization") String jwt, @RequestBody CreateChatRequest request) {
        User reqUser = userService.findUserByJwt(jwt);
        User user2 = userService.getUserById(request.getUserId());
        ChatResponse chatResponse = chatService.createChat(reqUser, user2);

        return ApiResponse.<ChatResponse>builder()
                .result(chatResponse)
                .build();
    }

    @GetMapping("/user")
    public ApiResponse<List<ChatResponse>> findUserChat(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);

        return ApiResponse.<List<ChatResponse>>builder()
                .result(chatService.findUserChat(user.getId()))
                .build();
    }
    @GetMapping()
    public ApiResponse<List<ChatResponse>> findAllChat(){
        return ApiResponse.<List<ChatResponse>>builder()
                .result(chatService.findAllChat())
                .build();
    }

    @GetMapping("/{chatId}")
    public ApiResponse<ChatResponse> findChatById(@PathVariable Integer chatId){
        return ApiResponse.<ChatResponse>builder()
                .result(chatService.findChatById(chatId))
                .build();
    }
}
