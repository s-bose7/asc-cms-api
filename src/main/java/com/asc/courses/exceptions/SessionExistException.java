package com.asc.courses.exceptions;


public class SessionExistException extends Exception {

    /**
     * Constructor that takes a message as input.
     * 
     * @param message the detail message explaining the cause of the exception.
     */
    public SessionExistException(String message) {
        super(message);
    }

    /**
     * Constructor that takes a throwable cause as input.
     * 
     * @param cause the cause of the exception, used to indicate the underlying reason.
     */
    public SessionExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor that takes both a message and a cause as input.
     * 
     * @param message the detail message explaining the cause of the exception.
     * @param cause   the cause of the exception, used to indicate the underlying reason.
     */
    public SessionExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
