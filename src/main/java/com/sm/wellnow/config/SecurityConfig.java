package com.sm.wellnow.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF for testing
                .csrf(AbstractHttpConfigurer::disable)

                // Authorization Rules
                .authorizeHttpRequests(auth -> auth

                  /*    .requestMatchers("/admin/**")
                        .hasAuthority("ROLE_ADMIN")

                        .requestMatchers("/user/**")
                        .hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")

                         .requestMatchers("/admin/**")
                         .hasRole("ADMIN")
                         .requestMatchers("/user/**")
                         .hasAnyRole("USER", "ADMIN")
*/
                         .anyRequest().authenticated()
                )

                // Basic Authentication
                .httpBasic(Customizer.withDefaults())

                // Logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(200);
                            response.getWriter().write("Logged out successfully");
                        })
                );

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {

        UserDetails user1 = User.withUsername("user123")
//                .password("{noop}password1")
                .password(passwordEncoder().encode("user123"))
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("admin123")
//                .password("{noop}admin123")
                .password(passwordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();

        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Only create users if they don't already exist

        if (!userDetailsManager.userExists("user1")) {
            userDetailsManager.createUser(user1);
        }
        if (!userDetailsManager.userExists("admin1")) {
            userDetailsManager.createUser(user2);
        }

        return userDetailsManager;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}