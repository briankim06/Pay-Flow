package com.payflow.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration via lambda style
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(org.springframework.security.config.annotation.web.builders.HttpSecurity http) throws Exception {
        http
                // Disable CSRF for stateless REST API
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Configuring a URL-based authorization
                .authorizeHttpRequests(auth -> auth
                        // Permit all requests to authentication endpoints
                        .requestMatchers("/auth/**").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // HTTP Basic authentication
                .httpBasic(Customizer.withDefaults());

        // add jwt filter here
        return http.build();
    }
}