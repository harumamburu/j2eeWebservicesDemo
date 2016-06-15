package com.my.lab.dao;

import com.my.lab.dao.entity.JPAEntity;

import java.util.Map;

public interface DbPersistent<T extends JPAEntity> extends Persistent<T> {

    T getEntityByNaturalId(Map<String, ?> naturalId);
    Class<T> getEntityClass();
}
