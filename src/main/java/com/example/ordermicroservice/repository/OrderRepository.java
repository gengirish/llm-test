package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@code OrderRepository} interface is a Spring Data JPA repository for managing {@link Order} entities.
 * It provides CRUD (Create, Read, Update, Delete) operations and other common database interactions
 * for the {@code Order} entity without requiring explicit implementation.
 *
 * <p>This interface extends {@link JpaRepository}, which includes methods for basic database operations,
 * pagination, and sorting. The {@code OrderRepository} works with the {@code Order} entity and uses
 * {@link Long} as the type of the entity's primary key.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see JpaRepository
 * @see Order
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}