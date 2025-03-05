package com.example.ordermicroservice.controller;

import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.service.Orchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The {@code OrderController} class is a REST controller responsible for handling HTTP requests
 * related to order processing. It exposes an endpoint for placing orders and delegates the
 * order processing logic to the {@link Orchestrator} service.
 *
 * <p>This class is annotated with {@link RestController} to indicate that it is a Spring MVC controller
 * with RESTful endpoints. It uses dependency injection to autowire the {@link Orchestrator} service.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see RestController
 * @see RequestMapping
 * @see Orchestrator
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    /**
     * The {@link Orchestrator} instance used to process orders.
     * This field is autowired by Spring to inject the service dependency.
     */
    @Autowired
    private Orchestrator orchestrator;

    /**
     * Handles HTTP POST requests to place a new order.
     *
     * <p>This method accepts an {@link Order} object in the request body, delegates the order processing
     * to the {@link Orchestrator}, and returns a message indicating the result of the order processing.
     *
     * @param order the {@link Order} object to be processed, provided in the request body (must not be {@code null}).
     * @return a message indicating the result of the order processing, such as "Order processed successfully"
     *         or "Order failed: Insufficient inventory".
     * @throws IllegalArgumentException if the provided order is {@code null}.
     */
    @PostMapping
    public String placeOrder(@RequestBody Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        return orchestrator.processOrder(order);
    }
}