package com.example.ordermicroservice.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The {@code GlobalExceptionHandlerTest} class is a JUnit 5 test class for the {@link GlobalExceptionHandler} class.
 * It tests the behavior of the exception handling methods, such as {@link #handleResourceNotFoundException(ResourceNotFoundException, WebRequest)}
 * and {@link #handleGlobalException(Exception, WebRequest)}, to ensure that they return the correct error responses.
 *
 * <p>This class is annotated with {@link ExtendWith(MockitoExtension.class)} to enable Mockito support
 * in JUnit 5. It uses {@link InjectMocks} to inject the {@link GlobalExceptionHandler} instance being tested.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 * @see GlobalExceptionHandler
 * @see ResourceNotFoundException
 * @see ErrorDetails
 * @see ExtendWith
 * @see InjectMocks
 */
@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    /**
     * The {@link GlobalExceptionHandler} instance being tested.
     */
    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * Tests the {@link GlobalExceptionHandler#handleResourceNotFoundException(ResourceNotFoundException, WebRequest)} method
     * when a {@link ResourceNotFoundException} is thrown.
     *
     * <p>This test verifies that the method returns a {@link ResponseEntity} with HTTP status 404 (Not Found)
     * and an {@link ErrorDetails} object containing the exception message and request details.
     */
    @Test
    void testHandleResourceNotFoundException() {
        // Arrange
        ResourceNotFoundException ex = new ResourceNotFoundException("Resource not found");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Test request details");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleResourceNotFoundException(ex, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Resource not found", response.getBody().getMessage());
        assertEquals("Test request details", response.getBody().getDetails());
        assertTrue(response.getBody().getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1))); // Ensure timestamp is recent
    }

    /**
     * Tests the {@link GlobalExceptionHandler#handleGlobalException(Exception, WebRequest)} method
     * when a generic {@link Exception} is thrown.
     *
     * <p>This test verifies that the method returns a {@link ResponseEntity} with HTTP status 500 (Internal Server Error)
     * and an {@link ErrorDetails} object containing the exception message and request details.
     */
    @Test
    void testHandleGlobalException() {
        // Arrange
        Exception ex = new Exception("Internal server error");
        WebRequest request = mock(WebRequest.class);
        when(request.getDescription(false)).thenReturn("Test request details");

        // Act
        ResponseEntity<ErrorDetails> response = globalExceptionHandler.handleGlobalException(ex, request);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Internal server error", response.getBody().getMessage());
        assertEquals("Test request details", response.getBody().getDetails());
        assertTrue(response.getBody().getTimestamp().isBefore(LocalDateTime.now().plusSeconds(1))); // Ensure timestamp is recent
    }
}