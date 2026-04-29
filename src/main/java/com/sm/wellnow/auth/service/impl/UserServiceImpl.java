package com.sm.wellnow.auth.service.impl;

import com.sm.wellnow.auth.dto.login.LoginRequest;
import com.sm.wellnow.auth.dto.register.RegisterRequest;
import com.sm.wellnow.auth.dto.register.RegisterResponse;
import com.sm.wellnow.auth.entity.User;
import com.sm.wellnow.auth.repository.UserRepository;
import com.sm.wellnow.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .build();
        User savedUser =  userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public RegisterResponse mapToResponse(User savedUser) {
        return RegisterResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();
    }

    @Override
    public User authenticate(LoginRequest loginRequest) {
        return null;
    }
}
