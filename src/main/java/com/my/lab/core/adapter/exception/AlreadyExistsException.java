package com.my.lab.core.adapter.exception;

public class AlreadyExistsException extends DataPersistenceException {

    public AlreadyExistsException(Throwable exc) {
        super(exc);
    }
}
