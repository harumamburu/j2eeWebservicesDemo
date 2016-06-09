package com.my.lab.core.adapter.exception;

public class AlreadyExistsException extends DataPersistenceException {

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
