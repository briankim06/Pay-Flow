package com.payflow.api.service;

import  com.payflow.api.model.Transaction;
import  com.payflow.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for handling payment transactions
 * Possible integration of Stripe API/payment gateway later
 */
@Service
public class PaymentService {

    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Processes a payment transaction
     * Simulating a successful payment by setting status to PROCESSED
     * @param transaction Transaction details
     * @return Saved transaction with updated status
     */
    public Transaction processPayment(Transaction transaction) {
        // Integrate w Stripe API
        transaction.setStatus("PROCESSED");
        // Save transaction metadata without saving sensitive information
        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves transaction by ID
     *
     * @param id The transaction ID
     * @return The transaction if found; null if not
     */
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
}