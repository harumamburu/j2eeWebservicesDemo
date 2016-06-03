package com.my.lab.core.adapter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.dao.DAO;
import com.my.lab.dao.db.DbBookDao;
import com.my.lab.dao.entity.Book;
import com.my.lab.core.dto.converter.BookConverter;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BookAdapter implements DAO<BookDTO> {

    @EJB
    private DbBookDao bookDao;

    @Override
    public BookDTO getEntity(Integer id) {
        return new BookConverter().convertToDTO(bookDao.getEntity(id));
    }

    @Override
    public BookDTO saveEntity(BookDTO book) {
        Converter<BookDTO, Book> converter = new BookConverter();
        return converter.convertToDTO(bookDao.saveEntity(converter.convertFromDTO(book)));
    }

    @Override
    public BookDTO updateEntity(BookDTO book) {
        Converter<BookDTO, Book> converter = new BookConverter();
        return converter.convertToDTO(bookDao.updateEntity(converter.convertFromDTO(book)));
    }

    @Override
    public BookDTO deleteEntity(Integer id) {
        return new BookConverter().convertToDTO(bookDao.deleteEntity(id));
    }
}
