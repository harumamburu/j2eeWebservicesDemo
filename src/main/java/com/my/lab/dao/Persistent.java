package com.my.lab.dao;

import com.my.lab.dao.entity.Entity;

public interface Persistent<T extends Entity> extends DAO<T> {

    Boolean contains(Integer id);
    <K extends Object> T getEntityByNaturalId(K naturalId);
    Class<T> getEntityClass();
}
