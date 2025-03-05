package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The {@code PaymentRepository} interface is a Spring Data JPA repository for managing {@link Payment} entities.
 * It provides CRUD (Create, Read, Update, Delete) operations and other common database interactions
 * for the {@code Payment} entity without requiring explicit implementation.
 *
 * <p>This interface extends {@link JpaRepository}, which includes methods for basic database operations,
 * pagination, and sorting. The {@code PaymentRepository} works with the {@code Payment} entity and uses
 * {@link Long} as the type of the entity's primary key.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see JpaRepository
 * @see Payment
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}