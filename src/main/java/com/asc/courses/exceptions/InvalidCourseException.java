package com.asc.courses.exceptions;

/**
 * Custom exception class to indicate that a course is not valid.
 * This exception extends the base `Exception` class, providing additional constructors
 * to specify a message, a cause, or both.
 */
public class InvalidCourseException extends Exception {

    /**
     * Constructor that takes a message as input.
     * 
     * @param message the detail message explaining the cause of the exception.
     */
    public InvalidCourseException(String message) {
        super(message);
    }

    /**
     * Constructor that takes a throwable cause as input.
     * 
     * @param cause the cause of the exception, used to indicate the underlying reason.
     */
    public InvalidCourseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor that takes both a message and a cause as input.
     * 
     * @param message the detail message explaining the cause of the exception.
     * @param cause   the cause of the exception, used to indicate the underlying reason.
     */
    public InvalidCourseException(String message, Throwable cause) {
        super(message, cause);
    }
}