package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Payment;
import com.example.ordermicroservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@code PaymentService} class is a service layer component responsible for handling business logic
 * related to {@link Payment} entities. It interacts with the {@link PaymentRepository} to perform
 * database operations such as processing payments.
 *
 * <p>This class is annotated with {@link Service} to indicate that it is a Spring-managed service
 * component. It uses dependency injection to autowire the {@link PaymentRepository} for database interactions.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see Service
 * @see Payment
 * @see PaymentRepository
 */
@Service
public class PaymentService {

    /**
     * The {@link PaymentRepository} instance used to interact with the database.
     * This field is autowired by Spring to inject the repository dependency.
     */
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * Processes a payment by setting its status to "SUCCESS" and saving it to the database.
     *
     * <p>This method updates the payment status to "SUCCESS" and persists the payment entity
     * using the {@link PaymentRepository}. It assumes that the payment processing is always successful
     * and returns {@code true} to indicate success.
     *
     * @param payment the {@link Payment} object to be processed (must not be {@code null}).
     * @return {@code true} to indicate that the payment was processed successfully.
     * @throws IllegalArgumentException if the provided payment is {@code null}.
     */
    public boolean processPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null");
        }
        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);
        return true;
    }
}