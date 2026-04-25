package com.sm.wellnow.service;

import com.sm.wellnow.dto.RegisterRequest;
import com.sm.wellnow.dto.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public RegisterResponse register(RegisterRequest registerRequest);
}
