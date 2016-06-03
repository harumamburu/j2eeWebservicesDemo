package com.my.lab.dao;

public interface DAO<T> {

    T saveEntity(T entity);
    T updateEntity(T entity);
    T getEntity(Integer id);
    T deleteEntity(Integer id);
}
