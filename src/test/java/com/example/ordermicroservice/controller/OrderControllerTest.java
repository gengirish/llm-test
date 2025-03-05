package com.example.ordermicroservice.controller;

import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.service.Orchestrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * The {@code OrderControllerTest} class is a JUnit 5 test class for the {@link OrderController} class.
 * It uses Mockito to mock the {@link Orchestrator} dependency and tests the behavior of the
 * {@code placeOrder} method under various scenarios.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link Mock} to create mock instances of dependencies and {@link InjectMocks} to
 * inject them into the {@code OrderController} instance being tested.
 *
 * @author Your Name
 * @version 1.0
 * @see OrderController
 * @see Orchestrator
 * @see Mock
 * @see InjectMocks
 * @see ExtendWith
 * @since 2023-10-01
 */
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    /**
     * A mock instance of {@link Orchestrator} used to simulate order processing.
     */
    @Mock
    private Orchestrator orchestrator;

    /**
     * The {@link OrderController} instance being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private OrderController orderController;

    /**
     * A sample {@link Order} object used for testing.
     */
    private Order order;

    /**
     * Initializes the test environment before each test case.
     * This method sets up a sample {@link Order} object with a product ID, quantity, and amount.
     */
    @BeforeEach
    void setUp() {
        // Initialize a sample order object for testing
        order = new Order();
        order.setId(1L);
        order.setProductId("PROD123");
        order.setQuantity(2);
        order.setAmount(100.0);
    }

    /**
     * Tests the {@link OrderController#placeOrder(Order)} method when the order is processed successfully.
     *
     * <p>This test verifies that the method returns the message "Order processed successfully" when the
     * order processing is successful.
     */
    @Test
    void testPlaceOrder_Success() {
        // Arrange
        when(orchestrator.processOrder(order)).thenReturn("Order processed successfully");

        // Act
        String result = orderController.placeOrder(order);

        // Assert
        assertEquals("Order processed successfully", result);
        verify(orchestrator, times(1)).processOrder(order);
    }

    /**
     * Tests the {@link OrderController#placeOrder(Order)} method when the order processing fails due to
     * insufficient inventory.
     *
     * <p>This test verifies that the method returns the message "Order failed: Insufficient inventory" when
     * the inventory check fails.
     */
    @Test
    void testPlaceOrder_InsufficientInventory() {
        // Arrange
        when(orchestrator.processOrder(order)).thenReturn("Order failed: Insufficient inventory");

        // Act
        String result = orderController.placeOrder(order);

        // Assert
        assertEquals("Order failed: Insufficient inventory", result);
        verify(orchestrator, times(1)).processOrder(order);
    }

    /**
     * Tests the {@link OrderController#placeOrder(Order)} method when the order processing fails due to
     * payment processing failure.
     *
     * <p>This test verifies that the method returns the message "Order failed: Payment processing failed" when
     * the payment processing fails.
     */
    @Test
    void testPlaceOrder_PaymentFailed() {
        // Arrange
        when(orchestrator.processOrder(order)).thenReturn("Order failed: Payment processing failed");

        // Act
        String result = orderController.placeOrder(order);

        // Assert
        assertEquals("Order failed: Payment processing failed", result);
        verify(orchestrator, times(1)).processOrder(order);
    }

    /**
     * Tests the {@link OrderController#placeOrder(Order)} method when the provided order is {@code null}.
     *
     * <p>This test verifies that the method throws an {@link IllegalArgumentException} when the provided
     * order is {@code null}.
     */
    @Test
    void testPlaceOrder_NullOrder() {
        // Arrange
        Order nullOrder = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orderController.placeOrder(nullOrder);
        });

        // Assert
        assertEquals("Order cannot be null", exception.getMessage());
        verify(orchestrator, never()).processOrder(any(Order.class));
    }
}