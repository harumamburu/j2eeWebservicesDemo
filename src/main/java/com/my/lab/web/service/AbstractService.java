package com.my.lab.web.service;

import com.my.lab.core.adapter.exception.AlreadyExistsException;
import com.my.lab.core.adapter.exception.DataPersistenceException;
import com.my.lab.core.adapter.exception.NotAllowedException;
import com.my.lab.web.entity.WebEntity;
import com.my.lab.web.error.EntityAlreadyExistsException;
import com.my.lab.web.error.EntityMisformattedException;
import com.my.lab.web.error.EntityNotFoundException;
import com.my.lab.web.error.InternalException;

public abstract class AbstractService<T extends WebEntity> implements Service<T> {

    public T onGet(Integer id) {
        try {
            return onGetRoutine(id);
        }  catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    protected abstract T onGetRoutine(Integer id) throws DataPersistenceException;

    public T onPost(T entity) {
        try {
            return onPostRoutine(entity);
        } catch (AlreadyExistsException exc) {
            throw new EntityAlreadyExistsException(exc.getMessage(), exc);
        } catch(NotAllowedException exc) {
            throw new EntityMisformattedException(exc);
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    protected abstract T onPostRoutine(T entity) throws DataPersistenceException;

    public T onPut(T entity) {
        try {
            return onPutRoutine(entity);
        } catch (AlreadyExistsException exc) {
            throw new EntityAlreadyExistsException(exc.getMessage(), exc);
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    protected abstract T onPutRoutine(T entity) throws DataPersistenceException;

    public T onDelete(Integer id) {
        try {
            return onDeleteRoutine(id);
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    protected abstract T onDeleteRoutine(Integer id) throws DataPersistenceException;

    protected void checkEntityNotNull(WebEntity entity, Integer id) {
        if (entity == null) {
            throw new EntityNotFoundException("No entity found by id = " + id);
        }
    }
}
