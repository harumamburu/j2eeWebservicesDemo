package com.my.lab.web.service;

import com.my.lab.core.adapter.BookAdapter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.web.entity.BookWebEntity;
import com.my.lab.web.entity.mapper.frommapper.BookWebFromDTOMapper;
import com.my.lab.web.entity.mapper.tomapper.BookWebToDTOMapper;

import javax.ejb.EJB;

public class BookService implements Service<BookWebEntity> {

    @EJB
    private BookAdapter bookAdapter;

    @Override
    public BookWebEntity onGet(Integer id) {
        BookDTO bookDTO = bookAdapter.getEntity(id);
        return BookWebFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }

    @Override
    public BookWebEntity onPost(BookWebEntity entity) {
        BookDTO bookDTO = BookWebToDTOMapper.INSTANCE.bookToDTO(entity);
        bookDTO = bookAdapter.saveEntity(bookDTO);
        return BookWebFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }

    @Override
    public BookWebEntity onPut(BookWebEntity entity) {
        BookDTO bookDTO = BookWebToDTOMapper.INSTANCE.bookToDTO(entity);
        bookDTO = bookAdapter.updateEntity(bookDTO);
        return BookWebFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }

    @Override
    public BookWebEntity onDelete(Integer id) {
        BookDTO bookDTO = bookAdapter.deleteEntity(id);
        return BookWebFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }
}
