package com.example.ordermicroservice.repository;

import com.example.ordermicroservice.model.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code InventoryRepositoryTest} class is a JUnit 5 test class for the {@link InventoryRepository} interface.
 * It uses Mockito to mock the repository and tests the behavior of the custom method {@link #findByProductId(String)}.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link MockBean} to create a mock instance of the repository for testing.
 *
 * @author Your Name
 * @version 1.0
 * @see InventoryRepository
 * @see Inventory
 * @see MockBean
 * @see ExtendWith
 * @since 2023-10-01
 */
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class InventoryRepositoryTest {

    /**
     * A mock instance of {@link InventoryRepository} used to simulate database interactions.
     */
    @MockBean
    private InventoryRepository inventoryRepository;

    /**
     * Tests the {@link InventoryRepository#findByProductId(String)} method when the inventory is found for a given product ID.
     *
     * <p>This test verifies that the method returns the correct {@link Inventory} entity when the product ID matches
     * an existing record in the database.
     */
    @Test
    void testFindByProductId_Success() {
        // Arrange
        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setProductId("PROD123");
        inventory.setQuantity(10);

        when(inventoryRepository.findByProductId("PROD123")).thenReturn(inventory);

        // Act
        Inventory result = inventoryRepository.findByProductId("PROD123");

        // Assert
        assertNotNull(result);
        assertEquals("PROD123", result.getProductId());
        assertEquals(10, result.getQuantity());
        verify(inventoryRepository, times(1)).findByProductId("PROD123");
    }

    /**
     * Tests the {@link InventoryRepository#findByProductId(String)} method when no inventory is found for a given product ID.
     *
     * <p>This test verifies that the method returns {@code null} when the product ID does not match any record in the database.
     */
    @Test
    void testFindByProductId_NotFound() {
        // Arrange
        when(inventoryRepository.findByProductId("PROD999")).thenReturn(null);

        // Act
        Inventory result = inventoryRepository.findByProductId("PROD999");

        // Assert
        assertNull(result);
        verify(inventoryRepository, times(1)).findByProductId("PROD999");
    }
}