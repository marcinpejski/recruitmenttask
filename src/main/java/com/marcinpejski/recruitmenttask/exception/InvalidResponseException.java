package com.marcinpejski.recruitmenttask.exception;

public class InvalidResponseException extends RuntimeException {
    public InvalidResponseException(String message, Throwable t) {
        super(message, t);
    }
}
