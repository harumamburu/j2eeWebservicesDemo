package com.my.lab.core.adapter;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.core.adapter.exception.AlreadyExistsException;
import com.my.lab.dao.DAO;
import com.my.lab.dao.db.DbAuthorDao;
import com.my.lab.dao.entity.AuthorJPAEntity;
import com.my.lab.dao.entity.mapper.frommapper.AuthorJPAFromDTOMapper;
import com.my.lab.dao.entity.mapper.tomapper.AuthorJPAToDTOMapper;
import com.my.lab.dao.exception.EntityAlreadyExistsException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AuthorAdapter implements DAO<AuthorDTO> {

    @EJB
    private DbAuthorDao authorDao;

    @Override
    public AuthorDTO getEntity(Integer id) {
        return authorToDTO(authorDao.getEntity(id));
    }

    @Override
    public AuthorDTO saveEntity(AuthorDTO authorDTO) throws AlreadyExistsException {
        try {
            return authorToDTO(authorDao.saveEntity(authorFromDTO(authorDTO)));
        } catch (EntityAlreadyExistsException exc) {
            throw new AlreadyExistsException(exc.getMessage(), exc);
        }

    }

    @Override
    public AuthorDTO updateEntity(AuthorDTO authorDTO) {
        return authorToDTO(authorDao.updateEntity(authorFromDTO(authorDTO)));
    }

    @Override
    public AuthorDTO deleteEntity(Integer id) {
        return authorToDTO(authorDao.deleteEntity(id));
    }

    private AuthorDTO authorToDTO(AuthorJPAEntity author) {
        return AuthorJPAToDTOMapper.INSTANCE.authorToDTO(author);
    }

    private AuthorJPAEntity authorFromDTO(AuthorDTO authorDTO) {
        return AuthorJPAFromDTOMapper.INSTANCE.authorFromDTO(authorDTO);
    }
}
