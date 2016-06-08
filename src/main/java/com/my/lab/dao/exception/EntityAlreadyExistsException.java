package com.my.lab.dao.exception;

public class EntityAlreadyExistsException extends DaoException {

    private final String messageId = "Entity with id=%d already exists";

    public EntityAlreadyExistsException() {
        super();
    }

    public EntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public void setEntityIdMessage(Long id) {
        message = String.format(messageId, id);
    }
}
