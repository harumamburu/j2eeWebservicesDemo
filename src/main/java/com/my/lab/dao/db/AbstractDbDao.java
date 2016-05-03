package com.my.lab.dao.db;

import com.my.lab.business.entity.Entity;
import com.my.lab.dao.DAO;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractDbDao<T extends Entity> implements DAO<T> {

    // TODO change EM initialization to container-managed
    @PersistenceContext(unitName = "demo-persistence-unit")
    private EntityManager entityManager;
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("demo-persistence-unit");

    protected abstract Class<T> getEntityClass();

    @Override
    public T saveEntity(T entity) {
        //entityManager = emf.createEntityManager();
        entityManager.persist(entity);
        //entityManager.close();
        return entity;
    }

    @Override
    public T updateEntity(T entity) {
        //entityManager = emf.createEntityManager();
        entity = entityManager.merge(entity);
        //entityManager.close();
        return entity;
    }

    @Override
    public T getEntity(Integer id) {
        //entityManager = emf.createEntityManager();
        T entity = entityManager.find(getEntityClass(), id);
        //entityManager.close();
        return entity;
    }

    @Override
    public T deleteEntity(Integer id) {
        //entityManager = emf.createEntityManager();
        T entity = entityManager.find(getEntityClass(), id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        //entityManager.close();
        return entity;
    }

    T executeQuerySingleResult(String queryName, Map<String, ?> params) {
        //entityManager = emf.createEntityManager();
        TypedQuery<T> query = getTypedQuery(queryName, params);
        //entityManager.close();
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
