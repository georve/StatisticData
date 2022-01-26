package com.auto.exception;

public class UnauthorizedClientException extends Exception {
    public UnauthorizedClientException(String message) {
        super(message);
    }
}
