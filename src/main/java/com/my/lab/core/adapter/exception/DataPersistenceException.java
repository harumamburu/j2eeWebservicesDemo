package com.my.lab.core.adapter.exception;

import com.my.lab.dao.exception.DaoException;

public class DataPersistenceException extends DaoException {

    public DataPersistenceException(Throwable exc) {
        super(exc);
    }
}
