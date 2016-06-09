package com.my.lab.dao;

import com.my.lab.dao.exception.DaoException;

public interface DAO<T> {

    T saveEntity(T entity) throws DaoException;
    T updateEntity(T entity);
    T getEntity(Integer id);
    T deleteEntity(Integer id);
}
