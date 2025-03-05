package com.example.ordermicroservice.exception;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * The {@code ErrorDetails} class represents a structured error response containing details about an exception.
 * It is used to provide consistent and informative error messages to clients when an exception occurs.
 *
 * <p>This class is annotated with {@link Data} from Lombok, which automatically generates getters, setters,
 * {@code toString}, {@code equals}, and {@code hashCode} methods.
 *
 * @author Your Name
 * @version 1.0
 * @see Data
 * @since 2023-10-01
 */
@Data
public class ErrorDetails {

    /**
     * The timestamp when the error occurred.
     * This field is automatically populated with the current date and time when the error is created.
     */
    private LocalDateTime timestamp;

    /**
     * A brief message describing the error.
     * This field typically contains a high-level description of what went wrong.
     */
    private String message;

    /**
     * Additional details about the error.
     * This field provides more specific information about the error, such as the context or cause.
     */
    private String details;

    /**
     * Constructs a new {@code ErrorDetails} object with the specified timestamp, message, and details.
     *
     * @param timestamp the timestamp when the error occurred (must not be {@code null}).
     * @param message   a brief message describing the error (must not be {@code null} or empty).
     * @param details   additional details about the error (must not be {@code null} or empty).
     * @throws IllegalArgumentException if any of the parameters are {@code null} or empty.
     */
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        if (timestamp == null) {
            throw new IllegalArgumentException("Timestamp cannot be null");
        }
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }
        if (details == null || details.isEmpty()) {
            throw new IllegalArgumentException("Details cannot be null or empty");
        }
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}