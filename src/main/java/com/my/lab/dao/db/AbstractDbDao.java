package com.my.lab.dao.db;

import com.my.lab.business.entity.Entity;
import com.my.lab.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractDbDao<T extends Entity> implements DAO<T> {

    //@PersistenceContext(unitName = "FX-persistence-unit")
    private EntityManager entityManager = Persistence.createEntityManagerFactory("demo-persistence-unit").createEntityManager();

    protected abstract Class<T> getEntityClass();

    @Override
    public T saveEntity(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T updateEntity(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public T getEntity(Integer id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public T deleteEntity(Integer id) {
        T entity = getEntity(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        return entity;
    }

    T executeQuerySingleResult(String queryName, Map<String, ?> params) {
        TypedQuery<T> query = getTypedQuery(queryName, params);
        return query.getSingleResult();
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

    <X extends Object> Map<String, X> getSingleParamMap(String key, X value) {
        Map<String, X> param = new HashMap<>();
        param.put(key, value);
        return param;
    }
}
