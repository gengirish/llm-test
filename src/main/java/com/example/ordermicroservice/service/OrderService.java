package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@code OrderService} class is a service layer component responsible for handling business logic
 * related to {@link Order} entities. It interacts with the {@link OrderRepository} to perform
 * database operations.
 *
 * <p>This class is annotated with {@link Service} to indicate that it is a Spring-managed service
 * component. It uses dependency injection to autowire the {@link OrderRepository} for database interactions.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see Service
 * @see Order
 * @see OrderRepository
 */
@Service
public class OrderService {

    /**
     * The {@link OrderRepository} instance used to interact with the database.
     * This field is autowired by Spring to inject the repository dependency.
     */
    @Autowired
    private OrderRepository orderRepository;

    /**
     * Creates a new order and saves it to the database.
     *
     * <p>This method sets the status of the order to "CREATED" and then uses the {@link OrderRepository}
     * to persist the order in the database.
     *
     * @param order the {@link Order} object to be created (must not be {@code null}).
     * @return the saved {@link Order} entity with updated fields (e.g., ID, status).
     * @throws IllegalArgumentException if the provided order is {@code null}.
     */
    public Order createOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        order.setStatus("CREATED");
        return orderRepository.save(order);
    }
}