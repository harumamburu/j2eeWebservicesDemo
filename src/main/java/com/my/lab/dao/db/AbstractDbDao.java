package com.my.lab.dao.db;

import com.my.lab.dao.entity.Entity;
import com.my.lab.dao.DAO;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractDbDao<T extends Entity> implements DAO<T> {

    @PersistenceContext(unitName = "demo-persistence-unit")
    private EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    @Override
    public T saveEntity(T entity) {
        // as ugly as it is, but this check is required to get rid of exceptions
        // on attempt to persist an entity with naturalId saved before
        T entityCheck = getEntityByNaturalId(entity.getNaturalId());
        if (entityCheck != null) {
            entity.setId(entityCheck.getId());
            entityManager.merge(entity);
        } else {
            entityManager.persist(entity);
        }
        return entity;
    }

    @Override
    public T updateEntity(T entity) {
        entity = entityManager.merge(entity);
        return entity;
    }

    @Override
    public T getEntity(Integer id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public T deleteEntity(Integer id) {
        T entity = entityManager.find(getEntityClass(), id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        return entity;
    }

    protected T executeQuerySingleResult(String queryName, Map<String, ?> params) {
        TypedQuery<T> query = getTypedQuery(queryName, params);
        try {
            return query.getSingleResult();
          // JPA throws an exception in case there's nothing matching the query instead of returning null
          // so here's a workaround
        } catch (NoResultException exc) {
            return null;
        }
    }

    private TypedQuery<T> getTypedQuery(String queryName, Map<String, ?> params) {
        TypedQuery<T> query = entityManager.createNamedQuery(queryName, getEntityClass());
        return setQueryParams(query, params);
    }

    private <T extends Query> T setQueryParams(T query, Map<String, ?> params) {
        for (Map.Entry<String, ?> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }
        return query;
    }

    protected <X extends Object> Map<String, X> getSingleParamMap(String key, X value) {
        Map<String, X> param = new HashMap<>();
        param.put(key, value);
        return param;
    }
}
