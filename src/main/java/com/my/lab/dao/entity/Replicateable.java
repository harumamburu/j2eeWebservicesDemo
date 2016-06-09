package com.my.lab.dao.entity;

/**
 * Imposes a contract on an implementing class to be able
 * to make field-to-field replicas of an objects of this class
 * <br/>
 * Thie idea is close to {@link Cloneable}, but
 * no new objects is being created
 * @param <T> extends {@link JPAEntity}
 */
public interface Replicateable<T extends JPAEntity> {

    /**
     * Makes a replica of a passed entity by
     * changing all it's fields with values of those
     * passed as a parameter
     * @param entity to take parameters values from
     */
    void replicate(T entity);
}
