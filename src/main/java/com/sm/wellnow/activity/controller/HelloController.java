package com.sm.wellnow.activity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/user/hello")
    public String helloUser() {
        return "Hello, USER!";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/hello")
    public String helloAdmin() {
        return "Hello, ADMIN";
    }
    @GetMapping("/check")
    public String check(Authentication auth) {
        return auth.getName() + " -> " + auth.getAuthorities();
    }
}
