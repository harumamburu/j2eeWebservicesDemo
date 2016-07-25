package com.my.lab.core.adapter.exception;

public class NotAllowedException extends DataPersistenceException {

    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
