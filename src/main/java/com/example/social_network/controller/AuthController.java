package com.example.social_network.controller;

import com.example.social_network.configuration.JwtPorvider;
import com.example.social_network.dto.request.LoginRequest;
import com.example.social_network.dto.request.UserCreateRequest;
import com.example.social_network.dto.response.ApiResponse;
import com.example.social_network.dto.response.AuthResponse;
import com.example.social_network.dto.response.UserResponse;
import com.example.social_network.entity.User;
import com.example.social_network.exception.AppException;
import com.example.social_network.exception.ErrorCode;
import com.example.social_network.mapper.UserMapper;
import com.example.social_network.repository.UserRepository;
import com.example.social_network.service.Impl.CustomerUserDetailsService;
import com.example.social_network.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    UserService userService;

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    UserMapper userMapper;

    CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/signUp")
    AuthResponse createUser(@RequestBody @Valid UserCreateRequest request) {
        User isExist = userRepository.findUserByEmail(request.getEmail());
        if(isExist!= null){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        User user = new User();

        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saveUser = userRepository.saveAndFlush(user);
        userMapper.toUserResponse(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(saveUser.getEmail(), saveUser.getPassword());

        String token = JwtPorvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "Register success");


        return authResponse;
    }
    @PostMapping("/signIn")
    public AuthResponse signin(@RequestBody LoginRequest request ){
        Authentication authentication = authentication(request.getEmail(), request.getPassword());

        String token = JwtPorvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token, "Login success");


        return authResponse;
    }

    private Authentication authentication(String email, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(email);

        if (userDetails == null){
            throw new AppException(ErrorCode.USERNAME_AUTH);
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new AppException(ErrorCode.PASSWORD_AUTH);
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
