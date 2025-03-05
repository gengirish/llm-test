package com.example.ordermicroservice.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code ResourceNotFoundExceptionTest} class is a JUnit 5 test class for the {@link ResourceNotFoundException} class.
 * It tests the behavior of the custom exception, including its constructor and validation logic.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5, although Mockito is not explicitly used in this test class.
 *
 * @author Your Name
 * @version 1.0
 * @see ResourceNotFoundException
 * @see ExtendWith
 * @since 2023-10-01
 */
@ExtendWith(MockitoExtension.class)
public class ResourceNotFoundExceptionTest {

    /**
     * Tests the {@link ResourceNotFoundException#ResourceNotFoundException(String)} constructor
     * when a valid error message is provided.
     *
     * <p>This test verifies that the exception is correctly instantiated with the provided message
     * and that the message can be retrieved using the {@link Throwable#getMessage()} method.
     */
    @Test
    void testConstructor_ValidMessage() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert
        assertNotNull(exception);
        assertEquals(errorMessage, exception.getMessage());
    }

    /**
     * Tests the {@link ResourceNotFoundException#ResourceNotFoundException(String)} constructor
     * when a {@code null} error message is provided.
     *
     * <p>This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the provided message is {@code null}.
     */
    @Test
    void testConstructor_NullMessage() {
        // Arrange
        String nullMessage = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ResourceNotFoundException(nullMessage);
        });

        // Assert
        assertEquals("Message cannot be null or empty", exception.getMessage());
    }

    /**
     * Tests the {@link ResourceNotFoundException#ResourceNotFoundException(String)} constructor
     * when an empty error message is provided.
     *
     * <p>This test verifies that the constructor throws an {@link IllegalArgumentException} when
     * the provided message is empty.
     */
    @Test
    void testConstructor_EmptyMessage() {
        // Arrange
        String emptyMessage = "";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new ResourceNotFoundException(emptyMessage);
        });

        // Assert
        assertEquals("Message cannot be null or empty", exception.getMessage());
    }
}