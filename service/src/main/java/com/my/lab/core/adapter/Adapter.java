package com.my.lab.core.adapter;

import com.my.lab.core.adapter.exception.AlreadyExistsException;
import com.my.lab.core.adapter.exception.DataPersistenceException;
import com.my.lab.core.adapter.exception.NotAllowedException;
import com.my.lab.dao.DAO;
import com.my.lab.dao.exception.DaoException;
import com.my.lab.dao.exception.EntityAlreadyExistsException;
import com.my.lab.dao.exception.EntityNotAllowedException;

public interface Adapter<T> extends DAO<T> {

    T saveEntity(T entity) throws DataPersistenceException;
    T updateEntity(T entity) throws DataPersistenceException;
    T getEntity(Integer id) throws DataPersistenceException;
    T deleteEntity(Integer id) throws DataPersistenceException;
}
