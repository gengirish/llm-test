package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@code InventoryRepository} interface is a Spring Data JPA repository for managing {@link Inventory} entities.
 * It provides CRUD (Create, Read, Update, Delete) operations and custom query methods for interacting with the database.
 *
 * <p>This interface extends {@link JpaRepository}, which includes methods for basic database operations,
 * pagination, and sorting. The {@code InventoryRepository} works with the {@code Inventory} entity and uses
 * {@link Long} as the type of the entity's primary key.
 *
 * <p>In addition to the standard CRUD operations, this repository provides a custom method
 * {@link #findByProductId(String)} to retrieve an {@code Inventory} entity by its associated product ID.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see JpaRepository
 * @see Inventory
 */
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Retrieves an {@link Inventory} entity by its associated product ID.
     *
     * <p>This method uses a Spring Data JPA query derivation mechanism to automatically generate
     * the database query based on the method name. It searches for an inventory record where the
     * {@code productId} matches the provided value.
     *
     * @param productId the product ID associated with the inventory record (must not be {@code null}).
     * @return the {@code Inventory} entity associated with the given product ID, or {@code null} if no match is found.
     */
    Inventory findByProductId(String productId);
}