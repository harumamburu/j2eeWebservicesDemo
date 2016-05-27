package com.my.lab.core.manager;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.db.DbAuthorDao;
import com.my.lab.dao.entity.Author;
import com.my.lab.dao.entity.converter.AuthorConverter;

import javax.ejb.EJB;

public class AuthorManager {

    @EJB
    private DbAuthorDao authorDao;

    public AuthorDTO getAuthor(Integer id) {
        return new AuthorConverter().convertToDTO(authorDao.getEntity(id));
    }

    public AuthorDTO saveAuthor(AuthorDTO author) {
        Converter<AuthorDTO, Author> converter = new AuthorConverter();
        return converter.convertToDTO(authorDao.saveEntity(converter.convertFromDTO(author)));
    }

    public AuthorDTO updateAuthor(AuthorDTO author) {
        Converter<AuthorDTO, Author> converter = new AuthorConverter();
        return converter.convertToDTO(authorDao.updateEntity(converter.convertFromDTO(author)));
    }

    public AuthorDTO deleteAuthor(Integer id) {
        return new AuthorConverter().convertToDTO(authorDao.deleteEntity(id));
    }
}
