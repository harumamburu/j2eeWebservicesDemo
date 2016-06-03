package com.my.lab.dao.memory;

import com.my.lab.dao.DBPoller;
import com.my.lab.dao.entity.AuthorJPAEntity;

import javax.ejb.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class InMemoryAuthorDao implements DBPoller<AuthorJPAEntity> {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Map<Integer, AuthorJPAEntity> AUTHORS = new ConcurrentHashMap<>(8, 0.9f, 1);

    @Override
    public AuthorJPAEntity saveEntity(AuthorJPAEntity author) {
        if (author.getId() == null) {
            author.setId(COUNTER.incrementAndGet());
        }
        AUTHORS.put(author.getId(), author);
        return author;
    }

    @Override
    public AuthorJPAEntity getEntity(Integer id) {
        return AUTHORS.get(id);
    }

    @Override
    public AuthorJPAEntity deleteEntity(Integer id) {
        AuthorJPAEntity author = AUTHORS.remove(id);
        if (author != null) {
            COUNTER.decrementAndGet();
        }
        return author;
    }

    @Override
    public <String> AuthorJPAEntity getEntityByNaturalId(String naturalId) {
        AuthorJPAEntity entity = null;
        for (AuthorJPAEntity author : AUTHORS.values()) {
            if (author.getName().equals(naturalId)) {
                entity = author;
                break;
            }
        }
        return entity;
    }

    @Override
    public Boolean contains(Integer id) {
        return AUTHORS.containsKey(id);
    }

    @Override
    public AuthorJPAEntity updateEntity(AuthorJPAEntity entity) {
        return saveEntity(entity);
    }
}
