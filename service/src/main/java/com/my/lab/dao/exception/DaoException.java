package com.my.lab.dao.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DaoException extends Exception {

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Builder builder) {
        super(builder.message);
        if (builder.cause != null) {
            initCause(builder.cause);
        }
    }

    protected abstract static class Builder {
        protected Throwable cause = null;
        protected String message = null;

        public Builder addCause(Throwable cause) {
            this.cause = cause;
            return this;
        }

        public abstract <E extends DaoException> E build();
    }
}
