package com.example.social_network.controller;

import com.example.social_network.dto.request.MessageRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.MessageResponse;
import com.example.social_network.entity.Message;
import com.example.social_network.entity.User;
import com.example.social_network.service.MessageService;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/messages")
public class MessageController {

    MessageService messageService;

    UserService userService;

    @PostMapping("/chat/{chatId}")
    public ApiResponse<MessageResponse> createMessage(@RequestBody MessageRequest request, @PathVariable Integer chatId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<MessageResponse>builder()
                .result(messageService.createMessage(user, chatId, request))
                .build();
    }

    @GetMapping("/chat/{chatId}")
    public ApiResponse<List<MessageResponse>> findMessageByChat(@PathVariable Integer chatId, @RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<List<MessageResponse>>builder()
                .result(messageService.findChatMessage(chatId))
                .build();
    }

}
