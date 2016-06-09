package com.my.lab.core.adapter;

import com.my.lab.core.dto.BookDTO;
import com.my.lab.core.adapter.exception.AlreadyExistsException;
import com.my.lab.dao.DAO;
import com.my.lab.dao.db.DbBookDao;
import com.my.lab.dao.entity.BookJPAEntity;
import com.my.lab.dao.entity.mapper.frommapper.BookJPAFromDTOMapper;
import com.my.lab.dao.entity.mapper.tomapper.BookJPAToDTOMapper;
import com.my.lab.dao.exception.EntityAlreadyExistsException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class BookAdapter implements DAO<BookDTO> {

    @EJB
    private DbBookDao bookDao;

    @Override
    public BookDTO getEntity(Integer id) {
        return bookToDTO(bookDao.getEntity(id));
    }

    @Override
    public BookDTO saveEntity(BookDTO bookDTO) throws AlreadyExistsException {
        try {
            return bookToDTO(bookDao.saveEntity(bookFromDTO(bookDTO)));
        } catch (EntityAlreadyExistsException exc) {
            throw new AlreadyExistsException(exc.getMessage(), exc);
        }
    }

    @Override
    public BookDTO updateEntity(BookDTO bookDTO) {
        return bookToDTO(bookDao.updateEntity(bookFromDTO(bookDTO)));
    }

    @Override
    public BookDTO deleteEntity(Integer id) {
        return bookToDTO(bookDao.deleteEntity(id));
    }

    private BookDTO bookToDTO(BookJPAEntity book) {
        return BookJPAToDTOMapper.INSTANCE.bookToDTO(book);
    }

    private BookJPAEntity bookFromDTO(BookDTO bookDTO) {
        return BookJPAFromDTOMapper.INSTANCE.bookFromDTO(bookDTO);
    }
}
