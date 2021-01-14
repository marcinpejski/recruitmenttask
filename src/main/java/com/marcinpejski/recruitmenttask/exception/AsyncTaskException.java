package com.marcinpejski.recruitmenttask.exception;

public class AsyncTaskException extends RuntimeException {
    public AsyncTaskException(String message, Throwable t) {
        super(message, t);
    }
}
