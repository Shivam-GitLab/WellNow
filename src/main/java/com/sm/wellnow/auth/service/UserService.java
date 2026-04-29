package com.sm.wellnow.auth.service;

import com.sm.wellnow.auth.dto.login.LoginRequest;
import com.sm.wellnow.auth.dto.register.RegisterRequest;
import com.sm.wellnow.auth.dto.register.RegisterResponse;
import com.sm.wellnow.auth.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public RegisterResponse register(RegisterRequest registerRequest);

    User authenticate(LoginRequest loginRequest);

    Object mapToResponse(User user);
}
