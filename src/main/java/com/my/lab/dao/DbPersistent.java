package com.my.lab.dao;

import com.my.lab.dao.entity.JPAEntity;

public interface DbPersistent<T extends JPAEntity> extends Persistent<T> {

    <K extends Object> T getEntityByNaturalId(K naturalId);
    Class<T> getEntityClass();
}
