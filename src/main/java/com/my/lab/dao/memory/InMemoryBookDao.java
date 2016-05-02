package com.my.lab.dao.memory;

import com.my.lab.business.entity.Book;
import com.my.lab.dao.DAO;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryBookDao implements DAO<Book> {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Map<Integer, Book> BOOKS = new HashMap<>();

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
    public Boolean contains(Integer id) {
        return BOOKS.containsKey(id);
    }

    @Override
    public Book updateEntity(Book entity) {
        return saveEntity(entity);
    }
}
