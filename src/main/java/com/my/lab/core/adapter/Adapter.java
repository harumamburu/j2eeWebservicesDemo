package com.my.lab.core.adapter;

import com.my.lab.core.adapter.exception.AlreadyExistsException;
import com.my.lab.core.adapter.exception.DataPersistenceException;
import com.my.lab.core.adapter.exception.NotAlowedException;
import com.my.lab.dao.DAO;
import com.my.lab.dao.exception.DaoException;
import com.my.lab.dao.exception.EntityAlreadyExistsException;
import com.my.lab.dao.exception.EntityNotAllowedException;

public interface Adapter<T> extends DAO<T> {

    @Override
    default T saveEntity(T entity) throws DaoException {
        try {
            return saveEntityRoutine(entity);
        } catch (EntityAlreadyExistsException exc) {
            throw new AlreadyExistsException(exc.getMessage(), exc);
        } catch (EntityNotAllowedException exc) {
            throw new NotAlowedException(exc.getMessage(), exc);
        } catch (DaoException exc) {
            throw new DataPersistenceException(exc.getMessage(), exc);
        }
    }

    T saveEntityRoutine(T entity) throws DaoException;

    @Override
    default T updateEntity(T entity) throws DaoException {
        try {
            return updateEntityRoutine(entity);
        } catch (EntityAlreadyExistsException exc) {
            throw new AlreadyExistsException(exc.getMessage(), exc);
        } catch (DaoException exc) {
            throw new DataPersistenceException(exc.getMessage(), exc);
        }
    }

    T updateEntityRoutine(T entity) throws DaoException;

    @Override
    default T getEntity(Integer id) throws DaoException {
        try {
            return getEntityRoutine(id);
        } catch (DaoException exc) {
            throw new DataPersistenceException(exc.getMessage(), exc);
        }
    }

    T getEntityRoutine(Integer id) throws DaoException;

    @Override
    default T deleteEntity(Integer id) throws DaoException {
        try {
            return deleteEntityRoutine(id);
        } catch (DaoException exc) {
            throw new DataPersistenceException(exc.getMessage(), exc);
        }
    }

    T deleteEntityRoutine(Integer id) throws DaoException;
}
