package com.my.lab.core.manager;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.dao.db.DbBookDao;
import com.my.lab.dao.entity.Book;
import com.my.lab.core.dto.converter.BookConverter;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BookManager {

    @EJB
    private DbBookDao bookDao;

    public BookDTO getBook(Integer id) {
        return new BookConverter().convertToDTO(bookDao.getEntity(id));
    }

    public BookDTO saveBook(BookDTO book) {
        Converter<BookDTO, Book> converter = new BookConverter();
        return converter.convertToDTO(bookDao.saveEntity(converter.convertFromDTO(book)));
    }

    public BookDTO updateBook(BookDTO book) {
        Converter<BookDTO, Book> converter = new BookConverter();
        return converter.convertToDTO(bookDao.updateEntity(converter.convertFromDTO(book)));
    }

    public BookDTO deleteBook(Integer id) {
        return new BookConverter().convertToDTO(bookDao.deleteEntity(id));
    }
}
