package com.my.lab.core.adapter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.DAO;
import com.my.lab.dao.db.DbAuthorDao;
import com.my.lab.core.dto.converter.AuthorConverter;
import com.my.lab.dao.entity.Author;

import javax.ejb.EJB;

public class AuthorAdapter implements DAO<AuthorDTO> {

    @EJB
    private DbAuthorDao authorDao;

    @Override
    public AuthorDTO getEntity(Integer id) {
        return new AuthorConverter().convertToDTO(authorDao.getEntity(id));
    }

    @Override
    public AuthorDTO saveEntity(AuthorDTO author) {
        Converter<AuthorDTO, Author> converter = new AuthorConverter();
        return converter.convertToDTO(authorDao.saveEntity(converter.convertFromDTO(author)));
    }

    @Override
    public AuthorDTO updateEntity(AuthorDTO author) {
        Converter<AuthorDTO, Author> converter = new AuthorConverter();
        return converter.convertToDTO(authorDao.updateEntity(converter.convertFromDTO(author)));
    }

    @Override
    public AuthorDTO deleteEntity(Integer id) {
        return new AuthorConverter().convertToDTO(authorDao.deleteEntity(id));
    }
}
