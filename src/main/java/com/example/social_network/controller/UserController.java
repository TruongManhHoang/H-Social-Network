package com.example.social_network.controller;

import com.example.social_network.dto.request.UserCreateRequest;
import com.example.social_network.dto.request.UserUpdateRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.UserResponse;
import com.example.social_network.entity.User;
import com.example.social_network.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class UserController {
    UserService userService;

    @PostMapping()
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.findAllUser())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<User> getUser(@PathVariable("id") Integer id) {
        return ApiResponse.<User>builder()
                .result(userService.getUserById(id))
                .build();
    }

    @DeleteMapping("/{Id}")
    ApiResponse<String> deleteUser(@PathVariable Integer Id) {
        userService.deleteUser(Id);
        return ApiResponse.<String>builder().result("User has been deleted").build();
    }

    @PutMapping()
    ApiResponse<UserResponse> updateUser(@RequestHeader("Authorization") String jwt, @RequestBody UserUpdateRequest request) {
        User user = userService.findUserByJwt(jwt);
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request, user.getId()))
                .build();
    }
    @PutMapping("follow/{id2}")
    public ApiResponse<UserResponse> followUserHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer id2){
        User reqUser = userService.findUserByJwt(jwt);
        return ApiResponse.<UserResponse>builder()
                .result(userService.followUser(reqUser.getId(),id2))
                .build();
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> users = userService.searchUser(query);

        return users;
    }

    @GetMapping("/profile")
    public UserResponse getUserFromToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        UserResponse userResponse = new UserResponse(user);
        return userResponse;
    }

}
