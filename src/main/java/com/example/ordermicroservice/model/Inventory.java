package com.example.ordermicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents an Inventory entity in the order management system.
 * This class is used to store and retrieve inventory data from the database.
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
public class Inventory {

    /**
     * Unique identifier for the inventory item.
     * This field is mapped to the primary key of the inventory in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ID of the product associated with the inventory item.
     * This field stores the product identifier for the inventory.
     */
    private String productId;

    /**
     * The quantity of the product available in inventory.
     * This field stores the number of units of the product in stock.
     */
    private int quantity;
}
