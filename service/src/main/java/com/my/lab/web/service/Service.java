package com.my.lab.web.service;

import com.my.lab.web.entity.WebEntity;

public interface Service<T extends WebEntity> {

    T onGet(Integer id);
    T onPost(T entity);
    T onPut(T entity);
    T onDelete(Integer id);
}
