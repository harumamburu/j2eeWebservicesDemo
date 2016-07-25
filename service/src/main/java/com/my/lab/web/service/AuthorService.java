package com.my.lab.web.service;

import com.my.lab.core.adapter.AuthorAdapter;
import com.my.lab.core.adapter.exception.DataPersistenceException;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.web.entity.AuthorWebEntity;
import com.my.lab.web.entity.mapper.frommapper.AuthorWebFromDTOMapper;
import com.my.lab.web.entity.mapper.tomapper.AuthorWebToDTOMapper;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AuthorService extends AbstractService<AuthorWebEntity> {

    @EJB
    private AuthorAdapter authorAdapter;

    @Override
    protected AuthorWebEntity onGetRoutine(Integer id) throws DataPersistenceException {
        AuthorWebEntity authorWebEntity = authorFromDTO(authorAdapter.getEntity(id));
        checkEntityNotNull(authorWebEntity, id);
        return authorWebEntity;
    }

    @Override
    protected AuthorWebEntity onPostRoutine(AuthorWebEntity author) throws DataPersistenceException {
        return authorFromDTO(authorAdapter.saveEntity(authorToDTO(author)));
    }

    @Override
    protected AuthorWebEntity onPutRoutine(AuthorWebEntity author) throws DataPersistenceException {
        return authorFromDTO(authorAdapter.updateEntity(authorToDTO(author)));
    }

    @Override
    protected AuthorWebEntity onDeleteRoutine(Integer id) throws DataPersistenceException {
        AuthorWebEntity author = authorFromDTO(authorAdapter.deleteEntity(id));
        checkEntityNotNull(author, id);
        return author;
    }

    private AuthorDTO authorToDTO(AuthorWebEntity author) {
        return AuthorWebToDTOMapper.INSTANCE.authorToDTO(author);
    }

    private AuthorWebEntity authorFromDTO(AuthorDTO authorDTO) {
        return AuthorWebFromDTOMapper.INSTANCE.authorFromDTO(authorDTO);
    }
}
