package com.my.lab.data.storage;

import com.my.lab.entity.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BookStorage {

    private static final AtomicInteger increment = new AtomicInteger();
    private static final Map<Integer, Book> books = new HashMap<>();

    public static Book addBook(Book book) {
        if (book.getId() == null) {
            book.setId(increment.incrementAndGet());
        }
        books.put(book.getId(), book);
        return book;
    }

    public static Book getBook(Integer id) {
        return books.get(id);
    }

    public static Book deleteBook(Integer id) {
        return books.remove(id);
    }

    public static boolean contains(Integer id) {
        return books.containsKey(id);
    }
}
