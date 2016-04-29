package com.my.lab.dao.memory;

import com.my.lab.business.entity.Author;
import com.my.lab.dao.DAO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryAuthorDao implements DAO<Author> {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Map<Integer, Author> AUTHORS = new HashMap<>();

    @Override
    public Author save(Author author) {
        if (author.getId() == null) {
            author.setId(COUNTER.incrementAndGet());
        }
        AUTHORS.put(author.getId(), author);
        return author;
    }

    @Override
    public Author get(Integer id) {
        return AUTHORS.get(id);
    }

    @Override
    public Author delete(Integer id) {
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
    public Author update(Author entity) {
        return save(entity);
    }
}
