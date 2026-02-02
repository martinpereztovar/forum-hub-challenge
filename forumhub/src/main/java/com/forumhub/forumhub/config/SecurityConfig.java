package com.forumhub.forumhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // For now, disable CSRF to allow POST requests from Insomnia without a token
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        // Allow creating topics without authentication (temporary for Day 3)
                        .requestMatchers(HttpMethod.POST, "/topics").permitAll()

                        // (Optional) allow health checks / basic testing
                        .requestMatchers("/error").permitAll()

                        // Everything else is also open for now (we will lock down when JWT is ready)
                        .anyRequest().permitAll()
                )

                // Keep defaults; we are not implementing JWT yet
                .httpBasic(Customizer.withDefaults())

                .build();
    }
}
