package com.sm.wellnow.auth.dto.login;

import com.sm.wellnow.auth.dto.register.RegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private RegisterResponse user;
}
