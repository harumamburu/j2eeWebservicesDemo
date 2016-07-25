package com.my.lab.web.service;

import com.my.lab.core.adapter.BookAdapter;
import com.my.lab.core.adapter.exception.DataPersistenceException;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.entity.mapper.frommapper.BookWebFromDTOMapper;
import com.my.lab.web.entity.mapper.tomapper.BookWebToDTOMapper;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class BookService extends AbstractService<BookWebEntity> {

    @EJB
    private BookAdapter bookAdapter;

    @Override
    protected  BookWebEntity onGetRoutine(Integer id) throws DataPersistenceException {
        BookWebEntity book = bookFromDTO(bookAdapter.getEntity(id));
        checkEntityNotNull(book, id);
        return book;
    }

    @Override
    protected  BookWebEntity onPostRoutine(BookWebEntity book) throws DataPersistenceException {
        return bookFromDTO(bookAdapter.saveEntity(bookToDTO(book)));
    }

    @Override
    protected  BookWebEntity onPutRoutine(BookWebEntity book) throws DataPersistenceException {
        return bookFromDTO(bookAdapter.updateEntity(bookToDTO(book)));
    }

    @Override
    protected  BookWebEntity onDeleteRoutine(Integer id) throws DataPersistenceException {
        BookWebEntity book = bookFromDTO(bookAdapter.deleteEntity(id));
        checkEntityNotNull(book, id);
        return book;
    }

    private BookDTO bookToDTO(BookWebEntity book) {
        return BookWebToDTOMapper.INSTANCE.bookToDTO(book);
    }

    private BookWebEntity bookFromDTO(BookDTO bookDTO) {
        return BookWebFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }
}
