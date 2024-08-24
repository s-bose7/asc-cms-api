package com.asc.courses.exceptions;

/**
 * Custom exception class to indicate that a course with the same code already exists.
 * This exception extends the base `Exception` class, providing additional constructors
 * to specify a message, a cause, or both.
 */
public class CourseExistException extends Exception {

    /**
     * Constructor that takes a message as input.
     * 
     * @param message the detail message explaining the cause of the exception.
     */
    public CourseExistException(String message) {
        super(message);
    }

    /**
     * Constructor that takes a throwable cause as input.
     * 
     * @param cause the cause of the exception, used to indicate the underlying reason.
     */
    public CourseExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor that takes both a message and a cause as input.
     * 
     * @param message the detail message explaining the cause of the exception.
     * @param cause   the cause of the exception, used to indicate the underlying reason.
     */
    public CourseExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
