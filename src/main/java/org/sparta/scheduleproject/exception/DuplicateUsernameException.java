package org.sparta.scheduleproject.exception;

public class DuplicateUsernameException extends RuntimeException {
    private final int statusCode;

    public DuplicateUsernameException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
