package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code OrderServiceTest} class is a test class that verifies the functionality of the {@link OrderService} class.
 * It uses Mockito to mock dependencies and JUnit 5 for writing and running unit tests.
 *
 * <p>This class is annotated with {@link ExtendWith} to integrate Mockito with JUnit 5, allowing the use of
 * Mockito annotations such as {@link Mock} and {@link InjectMocks}.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see ExtendWith
 * @see MockitoExtension
 * @see Mock
 * @see InjectMocks
 * @see OrderService
 * @see OrderRepository
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    /**
     * A mock instance of {@link OrderRepository} used to simulate database interactions.
     * This mock is injected into the {@link OrderService} instance being tested.
     */
    @Mock
    private OrderRepository orderRepository;

    /**
     * The {@link OrderService} instance being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private OrderService orderService;

    /**
     * Tests the {@link OrderService#createOrder(Order)} method to ensure it correctly creates an order
     * and sets its status to "CREATED".
     *
     * <p>This test method performs the following steps:
     * <ol>
     *   <li>Creates a sample {@link Order} object with predefined values.</li>
     *   <li>Mocks the behavior of the {@link OrderRepository#save(Order)} method to return the same order object.</li>
     *   <li>Calls the {@link OrderService#createOrder(Order)} method and verifies that the returned order is not null.</li>
     *   <li>Asserts that the status of the created order is set to "CREATED".</li>
     * </ol>
     */
    @Test
    void testCreateOrder() {
        // Arrange
        Order order = new Order();
        order.setProductId("product1");
        order.setQuantity(2);
        order.setAmount(100.0);

        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order createdOrder = orderService.createOrder(order);

        // Assert
        assertNotNull(createdOrder);
        assertEquals("CREATED", createdOrder.getStatus());
    }
}