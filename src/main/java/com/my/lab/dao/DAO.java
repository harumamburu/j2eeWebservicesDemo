package com.my.lab.dao;

import com.my.lab.business.entity.Entity;

public interface DAO<T extends Entity> {

    T saveEntity(T entity);
    T updateEntity(T entity);
    T getEntity(Integer id);
    T deleteEntity(Integer id);
    Boolean contains(Integer id);
    <K extends Object> T getEntityByNaturalId(K naturalId);
}
