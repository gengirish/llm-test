package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code OrchestratorTest} class is a JUnit 5 test class for the {@link Orchestrator} class.
 * It uses Mockito to mock the dependencies ({@link OrderService}, {@link InventoryService}, and {@link PaymentService})
 * and tests the behavior of the {@code Orchestrator#processOrder(Order)} method under various scenarios.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link Mock} to create mock instances of dependencies and {@link InjectMocks} to
 * inject them into the {@code Orchestrator} instance being tested.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see Orchestrator
 * @see OrderService
 * @see InventoryService
 * @see PaymentService
 * @see Mock
 * @see InjectMocks
 * @see ExtendWith
 */
@ExtendWith(MockitoExtension.class)
public class OrchestratorTest {

    /**
     * A mock instance of {@link OrderService} used to simulate order creation.
     */
    @Mock
    private OrderService orderService;

    /**
     * A mock instance of {@link InventoryService} used to simulate inventory checks and updates.
     */
    @Mock
    private InventoryService inventoryService;

    /**
     * A mock instance of {@link PaymentService} used to simulate payment processing.
     */
    @Mock
    private PaymentService paymentService;

    /**
     * The {@link Orchestrator} instance being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private Orchestrator orchestrator;

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
     * Tests the {@link Orchestrator#processOrder(Order)} method when the order is processed successfully.
     *
     * <p>This test verifies that the order processing workflow is executed correctly, including inventory checks,
     * payment processing, order creation, and inventory updates. It ensures that the method returns the
     * message "Order processed successfully".
     */
    @Test
    void testProcessOrder_Success() {
        // Arrange
        when(inventoryService.checkInventory("PROD123", 2)).thenReturn(true);
        when(paymentService.processPayment(any(Payment.class))).thenReturn(true);
        doNothing().when(orderService).createOrder(order);
        doNothing().when(inventoryService).updateInventory("PROD123", 2);

        // Act
        String result = orchestrator.processOrder(order);

        // Assert
        assertEquals("Order processed successfully", result);
        verify(inventoryService, times(1)).checkInventory("PROD123", 2);
        verify(paymentService, times(1)).processPayment(any(Payment.class));
        verify(orderService, times(1)).createOrder(order);
        verify(inventoryService, times(1)).updateInventory("PROD123", 2);
    }

    /**
     * Tests the {@link Orchestrator#processOrder(Order)} method when the inventory check fails due to insufficient stock.
     *
     * <p>This test verifies that the method returns the message "Order failed: Insufficient inventory" and
     * aborts the order processing workflow without proceeding to payment processing or order creation.
     */
    @Test
    void testProcessOrder_InsufficientInventory() {
        // Arrange
        when(inventoryService.checkInventory("PROD123", 2)).thenReturn(false);

        // Act
        String result = orchestrator.processOrder(order);

        // Assert
        assertEquals("Order failed: Insufficient inventory", result);
        verify(inventoryService, times(1)).checkInventory("PROD123", 2);
        verify(paymentService, never()).processPayment(any(Payment.class));
        verify(orderService, never()).createOrder(any(Order.class));
        verify(inventoryService, never()).updateInventory(anyString(), anyInt());
    }

    /**
     * Tests the {@link Orchestrator#processOrder(Order)} method when the payment processing fails.
     *
     * <p>This test verifies that the method returns the message "Order failed: Payment processing failed" and
     * aborts the order processing workflow without proceeding to order creation or inventory updates.
     */
    @Test
    void testProcessOrder_PaymentFailed() {
        // Arrange
        when(inventoryService.checkInventory("PROD123", 2)).thenReturn(true);
        when(paymentService.processPayment(any(Payment.class))).thenReturn(false);

        // Act
        String result = orchestrator.processOrder(order);

        // Assert
        assertEquals("Order failed: Payment processing failed", result);
        verify(inventoryService, times(1)).checkInventory("PROD123", 2);
        verify(paymentService, times(1)).processPayment(any(Payment.class));
        verify(orderService, never()).createOrder(any(Order.class));
        verify(inventoryService, never()).updateInventory(anyString(), anyInt());
    }

    /**
     * Tests the {@link Orchestrator#processOrder(Order)} method when the provided order is {@code null}.
     *
     * <p>This test verifies that the method throws an {@link IllegalArgumentException} when the provided
     * order is {@code null}.
     */
    @Test
    void testProcessOrder_NullOrder() {
        // Arrange
        Order nullOrder = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            orchestrator.processOrder(nullOrder);
        });

        // Assert
        assertEquals("Order cannot be null", exception.getMessage());
        verify(inventoryService, never()).checkInventory(anyString(), anyInt());
        verify(paymentService, never()).processPayment(any(Payment.class));
        verify(orderService, never()).createOrder(any(Order.class));
        verify(inventoryService, never()).updateInventory(anyString(), anyInt());
    }
}