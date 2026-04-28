package com.sm.wellnow.controller.auth;

import com.sm.wellnow.dto.register.RegisterRequest;
import com.sm.wellnow.dto.register.RegisterResponse;
import com.sm.wellnow.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

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


}
