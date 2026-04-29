package com.sm.wellnow.auth.controller;

import com.sm.wellnow.config.JwtUtils;
import com.sm.wellnow.auth.dto.register.RegisterRequest;
import com.sm.wellnow.auth.dto.register.RegisterResponse;
import com.sm.wellnow.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtUtils jwtUtils;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(registerRequest));
        /*
        return ResponseEntity.ok(userService.register(registerRequest));
        return new ResponseEntity<>(userService.register(registerRequest),HttpStatus.CREATED);
        */
    }
   /* @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userService.authenticate(loginRequest);
            String token = jwtUtils.generateToken(user.getId(), user.getRole().name());
            return ResponseEntity.ok(new LoginResponse(
                    token, userService.mapToResponse(user)
            ));

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }*/

}
