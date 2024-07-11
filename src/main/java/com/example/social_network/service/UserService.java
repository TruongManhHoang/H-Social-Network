package com.example.social_network.service;

import com.example.social_network.dto.request.UserCreateRequest;
import com.example.social_network.dto.request.UserUpdateRequest;
import com.example.social_network.dto.response.UserResponse;
import com.example.social_network.entity.User;

import java.util.List;

public interface UserService {

    public UserResponse createUser(UserCreateRequest request);

    public UserResponse updateUser(UserUpdateRequest request, Integer id);

    public User getUserById(Integer id);

    public List<UserResponse> findAllUser();

    public void deleteUser(Integer id);

    public UserResponse followUser(Integer id1, Integer id2);

    public List<User> searchUser(String query);

    public User findUserByJwt(String jwt);
}
