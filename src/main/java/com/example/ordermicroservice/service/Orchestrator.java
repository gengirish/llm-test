package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@code Orchestrator} class is a service layer component responsible for coordinating the order processing workflow.
 * It interacts with multiple services, including {@link OrderService}, {@link InventoryService}, and {@link PaymentService},
 * to ensure that orders are processed successfully.
 *
 * <p>This class is annotated with {@link Service} to indicate that it is a Spring-managed service component.
 * It uses dependency injection to autowire the necessary services for order processing.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see Service
 * @see OrderService
 * @see InventoryService
 * @see PaymentService
 */
@Service
public class Orchestrator {

    /**
     * The {@link OrderService} instance used to create and manage orders.
     * This field is autowired by Spring to inject the service dependency.
     */
    @Autowired
    private OrderService orderService;

    /**
     * The {@link InventoryService} instance used to check and update inventory levels.
     * This field is autowired by Spring to inject the service dependency.
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * The {@link PaymentService} instance used to process payments.
     * This field is autowired by Spring to inject the service dependency.
     */
    @Autowired
    private PaymentService paymentService;

    /**
     * Processes an order by coordinating the following steps:
     * <ol>
     *   <li>Check inventory availability for the requested product and quantity.</li>
     *   <li>Process the payment for the order.</li>
     *   <li>Create the order in the system.</li>
     *   <li>Update the inventory to reflect the deducted quantity.</li>
     * </ol>
     *
     * <p>If any step fails, the order processing is aborted, and an appropriate failure message is returned.
     *
     * @param order the {@link Order} object to be processed (must not be {@code null}).
     * @return a message indicating the result of the order processing, such as "Order processed successfully"
     *         or "Order failed: Insufficient inventory".
     * @throws IllegalArgumentException if the provided order is {@code null}.
     */
    public String processOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        // Step 1: Check inventory
        if (!inventoryService.checkInventory(order.getProductId(), order.getQuantity())) {
            return "Order failed: Insufficient inventory";
        }

        // Step 2: Process payment
        Payment payment = new Payment();
        payment.setOrderId(order.getId().toString());
        payment.setAmount(order.getAmount());
        if (!paymentService.processPayment(payment)) {
            return "Order failed: Payment processing failed";
        }

        // Step 3: Create order
        orderService.createOrder(order);

        // Step 4: Update inventory
        inventoryService.updateInventory(order.getProductId(), order.getQuantity());

        return "Order processed successfully";
    }
}