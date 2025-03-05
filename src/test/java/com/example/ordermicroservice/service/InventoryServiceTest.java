package com.example.ordermicroservice.service;

import com.example.ordermicroservice.model.Inventory;
import com.example.ordermicroservice.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code InventoryServiceTest} class is a JUnit 5 test class for the {@link InventoryService} class.
 * It uses Mockito to mock the {@link InventoryRepository} dependency and tests the behavior of the
 * {@code InventoryService} methods under various scenarios.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link Mock} to create mock instances of dependencies and {@link InjectMocks} to
 * inject them into the {@code InventoryService} instance being tested.
 *
 * @author Your Name
 * @version 1.0
 * @see InventoryService
 * @see InventoryRepository
 * @see Mock
 * @see InjectMocks
 * @see ExtendWith
 * @since 2023-10-01
 */
@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    /**
     * A mock instance of {@link InventoryRepository} used to simulate database interactions.
     */
    @Mock
    private InventoryRepository inventoryRepository;

    /**
     * The {@link InventoryService} instance being tested, with mocked dependencies injected.
     */
    @InjectMocks
    private InventoryService inventoryService;

    /**
     * A sample {@link Inventory} object used for testing.
     */
    private Inventory inventory;

    /**
     * Initializes the test environment before each test case.
     * This method sets up a sample {@link Inventory} object with a product ID and quantity.
     */
    @BeforeEach
    void setUp() {
        // Initialize a sample inventory object for testing
        inventory = new Inventory();
        inventory.setProductId("PROD123");
        inventory.setQuantity(10);
    }

    /**
     * Tests the {@link InventoryService#checkInventory(String, int)} method when the inventory has
     * sufficient quantity for the requested product.
     *
     * <p>This test verifies that the method returns {@code true} when the product is found and the
     * available quantity is greater than or equal to the requested quantity.
     */
    @Test
    void testCheckInventory_SufficientQuantity() {
        // Arrange
        when(inventoryRepository.findByProductId("PROD123")).thenReturn(inventory);

        // Act
        boolean result = inventoryService.checkInventory("PROD123", 5);

        // Assert
        assertTrue(result);
        verify(inventoryRepository, times(1)).findByProductId("PROD123");
    }

    /**
     * Tests the {@link InventoryService#checkInventory(String, int)} method when the inventory has
     * insufficient quantity for the requested product.
     *
     * <p>This test verifies that the method returns {@code false} when the product is found but the
     * available quantity is less than the requested quantity.
     */
    @Test
    void testCheckInventory_InsufficientQuantity() {
        // Arrange
        when(inventoryRepository.findByProductId("PROD123")).thenReturn(inventory);

        // Act
        boolean result = inventoryService.checkInventory("PROD123", 15);

        // Assert
        assertFalse(result);
        verify(inventoryRepository, times(1)).findByProductId("PROD123");
    }

    /**
     * Tests the {@link InventoryService#checkInventory(String, int)} method when the product is not found
     * in the inventory.
     *
     * <p>This test verifies that the method returns {@code false} when the product is not found in the
     * inventory.
     */
    @Test
    void testCheckInventory_ProductNotFound() {
        // Arrange
        when(inventoryRepository.findByProductId("PROD999")).thenReturn(null);

        // Act
        boolean result = inventoryService.checkInventory("PROD999", 5);

        // Assert
        assertFalse(result);
        verify(inventoryRepository, times(1)).findByProductId("PROD999");
    }

    /**
     * Tests the {@link InventoryService#updateInventory(String, int)} method when the inventory is
     * successfully updated for a valid product.
     *
     * <p>This test verifies that the quantity of the inventory is updated correctly and the
     * {@link InventoryRepository#save(Object)} method is called to persist the changes.
     */
    @Test
    void testUpdateInventory_Success() {
        // Arrange
        when(inventoryRepository.findByProductId("PROD123")).thenReturn(inventory);

        // Act
        inventoryService.updateInventory("PROD123", 3);

        // Assert
        assertEquals(7, inventory.getQuantity()); // Verify quantity is updated
        verify(inventoryRepository, times(1)).findByProductId("PROD123");
        verify(inventoryRepository, times(1)).save(inventory);
    }

    /**
     * Tests the {@link InventoryService#updateInventory(String, int)} method when the product is not found
     * in the inventory.
     *
     * <p>This test verifies that the {@link InventoryRepository#save(Object)} method is not called when
     * the product is not found in the inventory.
     */
    @Test
    void testUpdateInventory_ProductNotFound() {
        // Arrange
        when(inventoryRepository.findByProductId("PROD999")).thenReturn(null);

        // Act
        assertThrows(IllegalStateException.class, () -> inventoryService.updateInventory("PROD999", 3));

        // Assert
        verify(inventoryRepository, times(1)).findByProductId("PROD999");
        verify(inventoryRepository, never()).save(any()); // Ensure save is not called
    }
}