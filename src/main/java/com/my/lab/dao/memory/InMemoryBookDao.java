package com.my.lab.dao.memory;

import com.my.lab.dao.DBPoller;
import com.my.lab.dao.entity.Book;
import com.my.lab.dao.DAO;

import javax.ejb.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class InMemoryBookDao implements DBPoller<Book> {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Map<Integer, Book> BOOKS = new ConcurrentHashMap<>(8, 0.9f, 1);

    @Override
    public Book saveEntity(Book book) {
        if (book.getId() == null) {
            book.setId(COUNTER.incrementAndGet());
        }
        BOOKS.put(book.getId(), book);
        return book;
    }

    @Override
    public Book getEntity(Integer id) {
        return BOOKS.get(id);
    }

    @Override
    public Book deleteEntity(Integer id) {
        Book book = BOOKS.remove(id);
        if (book != null) {
            COUNTER.decrementAndGet();
        }
        return book;
    }

    @Override
    public <String> Book getEntityByNaturalId(String naturalId) {
        Book entity = null;
        for (Book book : BOOKS.values()) {
            if (book.getName().equals(naturalId)) {
                entity = book;
                break;
            }
        }
        return entity;
    }

    @Override
    public Boolean contains(Integer id) {
        return BOOKS.containsKey(id);
    }

    @Override
    public Book updateEntity(Book entity) {
        return saveEntity(entity);
    }
}
