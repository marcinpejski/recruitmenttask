package com.marcinpejski.recruitmenttask.exception;

public class ExternalRequestException extends RuntimeException {
    public ExternalRequestException(String message) {
        super(message);
    }
}
