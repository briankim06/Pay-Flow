package com.payflow.api.service;

import  com.payflow.api.model.User;
import  com.payflow.api.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service for handling user-related operations + authentication
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // BCrypt encoder for hashing and checking passwords
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // JWT secret key
    private final String JWT_SECRET = "secretkey";

    /**
     * Authenticates a user and returns a JWT if successful
     *
     * @param email       The user's email.
     * @param rawPassword The user's raw password.
     * @return A JWT string if authentication succeeds; otherwise, null.
     */
    public String authenticate(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            // Generate JWT token valid for 24 hours
            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                    .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                    .compact();
        }
        return null;
    }
}