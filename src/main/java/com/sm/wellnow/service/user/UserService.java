package com.sm.wellnow.service.user;

import com.sm.wellnow.dto.register.RegisterRequest;
import com.sm.wellnow.dto.register.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public RegisterResponse register(RegisterRequest registerRequest);
}
