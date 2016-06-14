package com.my.lab.dao.exception;

public class EntityNotAllowedException extends DaoException {

    public EntityNotAllowedException() {
        super();
    }

    public EntityNotAllowedException(String message) {
        super(message);
    }

    public EntityNotAllowedException(Throwable cause) {
        super(cause);
    }

    public EntityNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
