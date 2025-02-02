package com.payflow.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * User entity representing an application user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique email for the user
    @Column(unique = true, nullable = false)
    private String email;

    // Stored hashed password
    @Column(nullable = false)
    private String passwordHash;

    // User role
    @Column(nullable = false)
    private String role;
}