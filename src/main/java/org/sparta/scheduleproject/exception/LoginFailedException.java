package org.sparta.scheduleproject.exception;

public class LoginFailedException extends RuntimeException {
    private final int statusCode;

    public LoginFailedException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
