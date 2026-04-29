package com.sm.wellnow.user.service.impl;

import com.sm.wellnow.user.dto.register.RegisterRequest;
import com.sm.wellnow.user.dto.register.RegisterResponse;
import com.sm.wellnow.user.entity.User;
import com.sm.wellnow.user.repository.UserRepository;
import com.sm.wellnow.user.service.UserService;
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
    private static RegisterResponse mapToResponse(User savedUser) {
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
}
