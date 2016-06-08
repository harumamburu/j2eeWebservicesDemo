package com.my.lab.dao.exception;

public abstract class DaoException extends Exception {

    protected String message;

    public DaoException() {
        super();
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
