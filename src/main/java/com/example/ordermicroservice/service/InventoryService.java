package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Inventory;
import com.example.ordermicroservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The {@code InventoryService} class is a service layer component responsible for handling business logic
 * related to {@link Inventory} entities. It interacts with the {@link InventoryRepository} to perform
 * database operations such as checking and updating inventory levels.
 *
 * <p>This class is annotated with {@link Service} to indicate that it is a Spring-managed service
 * component. It uses dependency injection to autowire the {@link InventoryRepository} for database interactions.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see Service
 * @see Inventory
 * @see InventoryRepository
 */
@Service
public class InventoryService {

    /**
     * The {@link InventoryRepository} instance used to interact with the database.
     * This field is autowired by Spring to inject the repository dependency.
     */
    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Checks if the inventory has sufficient quantity for a given product.
     *
     * <p>This method retrieves the inventory record for the specified product ID and checks if the
     * available quantity is greater than or equal to the requested quantity.
     *
     * @param productId the ID of the product to check (must not be {@code null} or empty).
     * @param quantity the required quantity to check against the inventory.
     * @return {@code true} if the inventory has sufficient quantity, otherwise {@code false}.
     * @throws IllegalArgumentException if the product ID is {@code null} or empty.
     */
    public boolean checkInventory(String productId, int quantity) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory != null && inventory.getQuantity() >= quantity;
    }

    /**
     * Updates the inventory for a given product by reducing its quantity.
     *
     * <p>This method retrieves the inventory record for the specified product ID and reduces the
     * available quantity by the specified amount. The updated inventory is then saved back to the database.
     *
     * @param productId the ID of the product to update (must not be {@code null} or empty).
     * @param quantity the quantity to deduct from the inventory.
     * @throws IllegalArgumentException if the product ID is {@code null} or empty.
     * @throws IllegalStateException if the inventory record for the product ID is not found.
     */
    public void updateInventory(String productId, int quantity) {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or empty");
        }
        Inventory inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null) {
            throw new IllegalStateException("Inventory record not found for product ID: " + productId);
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
    }
}