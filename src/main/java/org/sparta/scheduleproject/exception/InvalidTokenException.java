package org.sparta.scheduleproject.exception;

public class InvalidTokenException extends RuntimeException {
    private final int statusCode;

    public InvalidTokenException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}