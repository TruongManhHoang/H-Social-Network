package com.example.social_network.mapper;

import com.example.social_network.dto.request.UserCreateRequest;
import com.example.social_network.dto.response.UserResponse;
import com.example.social_network.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public User toUser(UserCreateRequest request);

    public UserResponse toUserResponse(User user);
}
