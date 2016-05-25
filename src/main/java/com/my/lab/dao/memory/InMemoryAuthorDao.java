package com.my.lab.dao.memory;

import com.my.lab.business.entity.Author;
import com.my.lab.dao.DAO;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class InMemoryAuthorDao implements DAO<Author> {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Map<Integer, Author> AUTHORS = new ConcurrentHashMap<>(8, 0.9f, 1);

    @Override
    public Author saveEntity(Author author) {
        if (author.getId() == null) {
            author.setId(COUNTER.incrementAndGet());
        }
        AUTHORS.put(author.getId(), author);
        return author;
    }

    @Override
    public Author getEntity(Integer id) {
        return AUTHORS.get(id);
    }

    @Override
    public Author deleteEntity(Integer id) {
        Author author = AUTHORS.remove(id);
        if (author != null) {
            COUNTER.decrementAndGet();
        }
        return author;
    }

    @Override
    public Boolean contains(Integer id) {
        return AUTHORS.containsKey(id);
    }

    @Override
    public Author updateEntity(Author entity) {
        return saveEntity(entity);
    }
}
