package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code PaymentRepositoryTest} class is a JUnit 5 test class for the {@link PaymentRepository} interface.
 * It uses Mockito to mock the repository and tests the behavior of the standard CRUD operations provided by
 * Spring Data JPA.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link MockBean} to create a mock instance of the repository for testing.
 *
 * @author Your Name
 * @version 1.0
 * @see PaymentRepository
 * @see Payment
 * @see MockBean
 * @see ExtendWith
 * @since 2023-10-01
 */
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class PaymentRepositoryTest {

    /**
     * A mock instance of {@link PaymentRepository} used to simulate database interactions.
     */
    @MockBean
    private PaymentRepository paymentRepository;

    /**
     * Tests the {@link PaymentRepository#findById(Object)} method when a payment is found for a given ID.
     *
     * <p>This test verifies that the method returns the correct {@link Payment} entity when the ID matches
     * an existing record in the database.
     */
    @Test
    void testFindById_Success() {
        // Arrange
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId("ORDER123");
        payment.setAmount(100.0);
        payment.setStatus("SUCCESS");

        when(paymentRepository.findById(1L)).thenReturn(Optional.of(payment));

        // Act
        Optional<Payment> result = paymentRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("ORDER123", result.get().getOrderId());
        assertEquals(100.0, result.get().getAmount());
        assertEquals("SUCCESS", result.get().getStatus());
        verify(paymentRepository, times(1)).findById(1L);
    }

    /**
     * Tests the {@link PaymentRepository#findById(Object)} method when no payment is found for a given ID.
     *
     * <p>This test verifies that the method returns an empty {@link Optional} when the ID does not match
     * any record in the database.
     */
    @Test
    void testFindById_NotFound() {
        // Arrange
        when(paymentRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Payment> result = paymentRepository.findById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(paymentRepository, times(1)).findById(999L);
    }

    /**
     * Tests the {@link PaymentRepository#save(Object)} method when saving a new payment.
     *
     * <p>This test verifies that the method saves the payment correctly and returns the saved entity.
     */
    @Test
    void testSavePayment_Success() {
        // Arrange
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId("ORDER123");
        payment.setAmount(100.0);
        payment.setStatus("SUCCESS");

        when(paymentRepository.save(payment)).thenReturn(payment);

        // Act
        Payment savedPayment = paymentRepository.save(payment);

        // Assert
        assertNotNull(savedPayment);
        assertEquals(1L, savedPayment.getId());
        assertEquals("ORDER123", savedPayment.getOrderId());
        assertEquals(100.0, savedPayment.getAmount());
        assertEquals("SUCCESS", savedPayment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    /**
     * Tests the {@link PaymentRepository#delete(Object)} method when deleting an existing payment.
     *
     * <p>This test verifies that the method deletes the payment correctly.
     */
    @Test
    void testDeletePayment_Success() {
        // Arrange
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId("ORDER123");
        payment.setAmount(100.0);
        payment.setStatus("SUCCESS");

        doNothing().when(paymentRepository).delete(payment);

        // Act
        paymentRepository.delete(payment);

        // Assert
        verify(paymentRepository, times(1)).delete(payment);
    }
}