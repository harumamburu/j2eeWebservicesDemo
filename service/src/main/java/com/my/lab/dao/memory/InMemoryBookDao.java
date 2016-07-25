package com.my.lab.dao.memory;

import com.my.lab.dao.Persistent;
import com.my.lab.dao.entity.BookJPAEntity;

import javax.ejb.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class InMemoryBookDao implements Persistent<BookJPAEntity> {

    private static final AtomicInteger COUNTER = new AtomicInteger();
    private static final Map<Integer, BookJPAEntity> BOOKS = new ConcurrentHashMap<>(8, 0.9f, 1);

    @Override
    public BookJPAEntity saveEntity(BookJPAEntity book) {
        if (book.getId() == null) {
            book.setId(COUNTER.incrementAndGet());
        }
        BOOKS.put(book.getId(), book);
        return book;
    }

    @Override
    public BookJPAEntity getEntity(Integer id) {
        return BOOKS.get(id);
    }

    @Override
    public BookJPAEntity deleteEntity(Integer id) {
        BookJPAEntity book = BOOKS.remove(id);
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
    public BookJPAEntity updateEntity(BookJPAEntity entity) {
        return saveEntity(entity);
    }
}
