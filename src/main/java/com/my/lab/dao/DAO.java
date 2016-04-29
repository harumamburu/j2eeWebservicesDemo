package com.my.lab.dao;

import com.my.lab.business.entity.Entity;

public interface DAO<T extends Entity> {

    T save(T entity);
    T update(T entity);
    T get(Integer id);
    T delete(Integer id);
    Boolean contains(Integer id);
}
