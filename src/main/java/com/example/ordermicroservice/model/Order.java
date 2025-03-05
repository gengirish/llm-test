package com.example.ordermicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents an Order entity in the order management system.
 * This class is used to store and retrieve order data from the database.
 * It is annotated as a JPA entity and is mapped to a database table.
 * The Lombok @Data annotation generates boilerplate code for getters, setters,
 * equals(), hashCode(), and toString() methods automatically.
 *
 * @author Your Name
 * @version 1.0
 * @since 2025-03-05
 */
@Data
@Entity
public class Order {

    /**
     * Unique identifier for the order.
     * This field is mapped to the primary key of the order in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID of the product associated with the order.
     * This field stores the product identifier related to the order.
     */
    private String productId;

    /**
     * The quantity of the product ordered.
     * This field stores the number of units of the product in the order.
     */
    private int quantity;

    /**
     * The total amount of the order.
     * This field stores the calculated total price for the order.
     */
    private double amount;

    /**
     * The status of the order.
     * This field indicates the current state of the order (e.g., "Pending", "Shipped", "Delivered").
     */
    private String status;
}
