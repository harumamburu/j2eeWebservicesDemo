package com.my.lab.dao.db;

import com.my.lab.business.entity.Entity;
import com.my.lab.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractDbDao<T extends Entity> implements DAO<T> {

    @PersistenceContext(unitName = "FX-persistence-unit")
    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public T get(Integer id) {
        return entityManager.find(getEntityClass(), id);
    }

    T executeNamedQuerySingleResult(String queryName, Map<String, ?> params) {
        TypedQuery<T> query = getTypedQuery(queryName, params);
        return query.getSingleResult();
    }

    private TypedQuery<T> getTypedQuery(String queryName, Map<String, ?> params) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, getEntityClass());
        for (Map.Entry<String, ?> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query;
    }

    <X extends Object> Map<String, X> getSingleParamMap(String key, X value) {
        return new HashMap<String, X>() {{ put(key, value); }};
    }
}
