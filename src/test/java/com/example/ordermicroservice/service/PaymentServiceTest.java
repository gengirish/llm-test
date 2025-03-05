package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Payment;
import com.example.ordermicroservice.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code PaymentServiceTest} class is a JUnit 5 test class for the {@link PaymentService} class.
 * It uses Mockito to mock the {@link PaymentRepository} dependency and tests the behavior of the
 * {@code PaymentService#processPayment(Payment)} method under various scenarios.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link Mock} to create mock instances of dependencies and {@link InjectMocks} to
 * inject them into the {@code PaymentService} instance being tested.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see PaymentService
 * @see PaymentRepository
 * @see Mock
 * @see InjectMocks
 * @see ExtendWith
 */
@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    /**
     * A mock instance of {@link PaymentRepository} used to simulate database interactions.
     */
    @Mock
    private PaymentRepository paymentRepository;

    /**
     * The {@link PaymentService} instance being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private PaymentService paymentService;

    /**
     * A sample {@link Payment} object used for testing.
     */
    private Payment payment;

    /**
     * Initializes the test environment before each test case.
     * This method sets up a sample {@link Payment} object with a status and other necessary fields.
     */
    @BeforeEach
    void setUp() {
        // Initialize a sample payment object for testing
        payment = new Payment();
        payment.setId(1L);
        payment.setOrderId("ORDER123");
        payment.setAmount(100.0);
        payment.setStatus("PENDING");
    }

    /**
     * Tests the {@link PaymentService#processPayment(Payment)} method when the payment is processed successfully.
     *
     * <p>This test verifies that the payment status is updated to "SUCCESS" and the payment is saved
     * to the database using the {@link PaymentRepository}. It also ensures that the method returns {@code true}
     * to indicate successful processing.
     */
    @Test
    void testProcessPayment_Success() {

        // Act
        boolean result = paymentService.processPayment(payment);

        // Assert
        assertTrue(result);
        assertEquals("SUCCESS", payment.getStatus()); // Verify status is updated
        verify(paymentRepository, times(1)).save(payment); // Verify save is called
    }

    /**
     * Tests the {@link PaymentService#processPayment(Payment)} method when the payment is {@code null}.
     *
     * <p>This test verifies that the method throws an {@link IllegalArgumentException} when the provided
     * payment is {@code null}.
     */
    @Test
    void testProcessPayment_NullPayment() {
        // Arrange
        Payment nullPayment = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.processPayment(nullPayment);
        });

        // Assert
        assertEquals("Payment cannot be null", exception.getMessage());
        verify(paymentRepository, never()).save(any()); // Ensure save is not called
    }
}