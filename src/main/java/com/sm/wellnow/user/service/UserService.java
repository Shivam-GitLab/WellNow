package com.sm.wellnow.user.service;

import com.sm.wellnow.user.dto.register.RegisterRequest;
import com.sm.wellnow.user.dto.register.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public RegisterResponse register(RegisterRequest registerRequest);
}
