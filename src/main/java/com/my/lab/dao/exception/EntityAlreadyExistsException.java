package com.my.lab.dao.exception;

public class EntityAlreadyExistsException extends DaoException {

    public EntityAlreadyExistsException() {
        super();
    }

    public EntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public void setEntityIdMessage(Long id) {
        message = String.format("Entity with id=%d already exists", id);
    }
}
