package com.my.lab.web.service;

import com.my.lab.core.adapter.BookAdapter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.core.adapter.exception.AlreadyExistsException;
import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.entity.mapper.frommapper.BookWebFromDTOMapper;
import com.my.lab.web.entity.mapper.tomapper.BookWebToDTOMapper;
import com.my.lab.web.error.EntityAlreadyExistsException;
import com.my.lab.web.error.EntityMisformattedException;
import com.my.lab.web.error.EntityNotFoundException;
import com.my.lab.web.error.InternalException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.NotAllowedException;

@Stateless
@LocalBean
public class BookService implements Service<BookWebEntity> {

    @EJB
    private BookAdapter bookAdapter;

    @Override
    public BookWebEntity onGet(Integer id) {
        try {
            BookWebEntity book = bookFromDTO(bookAdapter.getEntity(id));
            checkBookNotNull(book, id);
            return book;
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    @Override
    public BookWebEntity onPost(BookWebEntity book) {
        try {
            return bookFromDTO(bookAdapter.saveEntity(bookToDTO(book)));
        } catch (AlreadyExistsException exc) {
            throw new EntityAlreadyExistsException(exc.getMessage(), exc);
        } catch(NotAllowedException exc) {
            throw new EntityMisformattedException(exc);
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    @Override
    public BookWebEntity onPut(BookWebEntity book) {
        try {
            return bookFromDTO(bookAdapter.updateEntity(bookToDTO(book)));
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    @Override
    public BookWebEntity onDelete(Integer id) {
        try {
            BookWebEntity book = bookFromDTO(bookAdapter.deleteEntity(id));
            checkBookNotNull(book, id);
            return book;
        } catch (Exception exc) {
            throw new InternalException(exc);
        }
    }

    private BookDTO bookToDTO(BookWebEntity book) {
        return BookWebToDTOMapper.INSTANCE.bookToDTO(book);
    }

    private BookWebEntity bookFromDTO(BookDTO bookDTO) {
        return BookWebFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }

    private void checkBookNotNull(BookWebEntity book, Integer id) {
        if (book == null) {
            throw new EntityNotFoundException("No book found by id = " + id);
        }
    }
}
