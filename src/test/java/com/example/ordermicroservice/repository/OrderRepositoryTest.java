package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code OrderRepositoryTest} class is a JUnit 5 test class for the {@link OrderRepository} interface.
 * It uses Mockito to mock the repository and tests the behavior of the standard CRUD operations provided by
 * Spring Data JPA.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link MockBean} to create a mock instance of the repository for testing.
 *
 * @author Your Name
 * @version 1.0
 * @see OrderRepository
 * @see Order
 * @see MockBean
 * @see ExtendWith
 * @since 2023-10-01
 */
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class OrderRepositoryTest {

    /**
     * A mock instance of {@link OrderRepository} used to simulate database interactions.
     */
    @MockBean
    private OrderRepository orderRepository;

    /**
     * Tests the {@link OrderRepository#findById(Object)} method when an order is found for a given ID.
     *
     * <p>This test verifies that the method returns the correct {@link Order} entity when the ID matches
     * an existing record in the database.
     */
    @Test
    void testFindById_Success() {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setProductId("PROD123");
        order.setQuantity(2);
        order.setAmount(100.0);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        Optional<Order> result = orderRepository.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("PROD123", result.get().getProductId());
        assertEquals(2, result.get().getQuantity());
        assertEquals(100.0, result.get().getAmount());
        verify(orderRepository, times(1)).findById(1L);
    }

    /**
     * Tests the {@link OrderRepository#findById(Object)} method when no order is found for a given ID.
     *
     * <p>This test verifies that the method returns an empty {@link Optional} when the ID does not match
     * any record in the database.
     */
    @Test
    void testFindById_NotFound() {
        // Arrange
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        // Act
        Optional<Order> result = orderRepository.findById(999L);

        // Assert
        assertFalse(result.isPresent());
        verify(orderRepository, times(1)).findById(999L);
    }

    /**
     * Tests the {@link OrderRepository#save(Object)} method when saving a new order.
     *
     * <p>This test verifies that the method saves the order correctly and returns the saved entity.
     */
    @Test
    void testSaveOrder_Success() {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setProductId("PROD123");
        order.setQuantity(2);
        order.setAmount(100.0);

        when(orderRepository.save(order)).thenReturn(order);

        // Act
        Order savedOrder = orderRepository.save(order);

        // Assert
        assertNotNull(savedOrder);
        assertEquals(1L, savedOrder.getId());
        assertEquals("PROD123", savedOrder.getProductId());
        assertEquals(2, savedOrder.getQuantity());
        assertEquals(100.0, savedOrder.getAmount());
        verify(orderRepository, times(1)).save(order);
    }

    /**
     * Tests the {@link OrderRepository#delete(Object)} method when deleting an existing order.
     *
     * <p>This test verifies that the method deletes the order correctly.
     */
    @Test
    void testDeleteOrder_Success() {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setProductId("PROD123");
        order.setQuantity(2);
        order.setAmount(100.0);

        doNothing().when(orderRepository).delete(order);

        // Act
        orderRepository.delete(order);

        // Assert
        verify(orderRepository, times(1)).delete(order);
    }
}