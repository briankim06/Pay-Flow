package com.payflow.api.controller;

import com.payflow.api.model.Transaction;
import com.payflow.api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling payment-related endpoints requiring JWT authentication
 *
 */
@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    /**
     * Endpoint to process a new payment transaction
     *
     * @param transaction Transaction details
     * @return Processed transaction
     */
    @PostMapping
    public Transaction processTransaction(@RequestBody Transaction transaction) {
        return paymentService.processPayment(transaction);
    }

    /**
     * Endpoint to retrieve transaction by ID
     *
     * @param id Transaction ID
     * @return Transaction details
     */
    @GetMapping("/{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return paymentService.getTransactionById(id);
    }
}