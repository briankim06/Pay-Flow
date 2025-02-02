package com.payflow.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Transaction entity representing a payment transaction
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ID of the user making the transaction
    @Column(nullable = false)
    private Long userId;

    // Transaction amount
    @Column(nullable = false)
    private Double amount;

    // Payment method used
    @Column(nullable = false)
    private String paymentMethod;

    // Transaction status
    @Column(nullable = false)
    private String status;

    // Timestamp of transaction creation
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Pre-persist callback to set creation time if not provided
    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}