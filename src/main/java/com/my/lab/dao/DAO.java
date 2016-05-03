package com.my.lab.dao;

import com.my.lab.business.entity.Entity;

import javax.ejb.Local;

@Local
public interface DAO<T extends Entity> {

    T saveEntity(T entity);
    T updateEntity(T entity);
    T getEntity(Integer id);
    T deleteEntity(Integer id);
    Boolean contains(Integer id);
}
