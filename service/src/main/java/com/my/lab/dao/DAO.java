package com.my.lab.dao;

import com.my.lab.dao.exception.DaoException;

public interface DAO<T> {

    T saveEntity(T entity) throws DaoException;
    T updateEntity(T entity)throws DaoException;
    T getEntity(Integer id)throws DaoException;
    T deleteEntity(Integer id)throws DaoException;
}
