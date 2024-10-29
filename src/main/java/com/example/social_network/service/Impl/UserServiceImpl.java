package com.example.social_network.service.Impl;

import com.example.social_network.configuration.JwtPorvider;
import com.example.social_network.dto.request.UserCreateRequest;
import com.example.social_network.dto.request.UserUpdateRequest;
import com.example.social_network.dto.response.UserResponse;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.UserMapper;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    UserMapper userMapper;

    PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createUser(UserCreateRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.saveAndFlush(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest request, Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user1 = user.get();

        if (request.getFirstName() != null) {
            user1.setFirstName(request.getFirstName());
        }
        if (request.getPassword() != null) {
            user1.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        if (request.getLastName() != null) {
            user1.setLastName(request.getLastName());
        }
        if (request.getDob() != null) {
            user1.setDob(request.getDob());
        }
        if (request.getGender() != null) {
            user1.setGender(request.getGender());
        }
        if (request.getEmail() != null) {
            user1.setEmail(request.getEmail());
        }

        return userMapper.toUserResponse(userRepository.save(user1));
    }


    @Override
    public List<UserResponse> findAllUser() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public UserResponse findUserByEmail(String Email){
        return userMapper.toUserResponse(userRepository.findUserByEmail(Email));
    }
    public UserResponse followUser(Integer reqUserId, Integer id2){
        User reqUser = userRepository.findById(reqUserId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        User user2 = userRepository.findById(id2)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // Kiểm tra và khởi tạo danh sách followers và followings nếu cần
        if (user2.getFollowers() == null) {
            user2.setFollowers(new ArrayList<>());
        }
        if (reqUser.getFollowings() == null) {
            reqUser.setFollowings(new ArrayList<>());
        }

        // Kiểm tra trạng thái theo dõi và cập nhật danh sách
        if (user2.getFollowers().contains(reqUser.getId())) {
            user2.getFollowers().remove(reqUser.getId());
            reqUser.getFollowings().remove(user2.getId());
        } else {
            user2.getFollowers().add(reqUser.getId());
            reqUser.getFollowings().add(user2.getId());
        }

        reqUser = userRepository.save(reqUser);
        userRepository.save(user2);

        return userMapper.toUserResponse(reqUser);
    }

    public List<User> searchUser(String query){
        List<User> user = userRepository.searchUser(query);
        return user;
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtPorvider.getEmailFromJwtToken(jwt);

        User user = userRepository.findUserByEmail(email);
        return user;
    }
}
