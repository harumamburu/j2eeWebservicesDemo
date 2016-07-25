package com.my.lab.dao;

import com.my.lab.dao.entity.JPAEntity;

public interface Persistent<T extends JPAEntity> extends DAO<T> {

    Boolean contains(Integer id);
}
