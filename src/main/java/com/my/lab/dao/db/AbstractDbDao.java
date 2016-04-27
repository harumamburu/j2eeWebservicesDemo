package com.my.lab.dao.db;

import com.my.lab.dao.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public abstract class AbstractDbDao implements DAO {

    @PersistenceUnit
    private EntityManagerFactory emf;
    private EntityManager manager;
}
