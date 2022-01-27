package com.auto.exception;

public class IllegalArgumentClientException extends Exception {
    public IllegalArgumentClientException(String illegal_parameter_communication) {
        super(illegal_parameter_communication);
    }
}
