package com.example.ordermicroservice.exception;

/**
 * The {@code ResourceNotFoundException} class is a custom runtime exception used to indicate
 * that a requested resource could not be found. This exception is typically thrown when
 * an operation attempts to access or manipulate a resource that does not exist in the system.
 *
 * <p>This class extends {@link RuntimeException}, making it an unchecked exception that does not
 * require explicit handling in method signatures.
 *
 * @author Your Name
 * @version 1.0
 * @see RuntimeException
 * @since 2023-10-01
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code ResourceNotFoundException} with the specified error message.
     *
     * @param message the detail message describing the reason for the exception (must not be {@code null} or empty).
     * @throws IllegalArgumentException if the provided message is {@code null} or empty.
     */
    public ResourceNotFoundException(String message) {
        super(message);
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
    }
}