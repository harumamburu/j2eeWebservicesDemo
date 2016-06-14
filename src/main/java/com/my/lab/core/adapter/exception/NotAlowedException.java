package com.my.lab.core.adapter.exception;

public class NotAlowedException extends DataPersistenceException {

    public NotAlowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
