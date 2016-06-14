package com.my.lab.core.adapter;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.db.DbAuthorDao;
import com.my.lab.dao.entity.AuthorJPAEntity;
import com.my.lab.dao.entity.mapper.frommapper.AuthorJPAFromDTOMapper;
import com.my.lab.dao.entity.mapper.tomapper.AuthorJPAToDTOMapper;
import com.my.lab.dao.exception.DaoException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class AuthorAdapter implements Adapter<AuthorDTO> {

    @EJB
    private DbAuthorDao authorDao;

    @Override
    public AuthorDTO saveEntityRoutine(AuthorDTO authorDTO) throws DaoException {
        return authorToDTO(authorDao.saveEntity(authorFromDTO(authorDTO)));
    }

    @Override
    public AuthorDTO updateEntityRoutine(AuthorDTO authorDTO) throws DaoException {
        return authorToDTO(authorDao.updateEntity(authorFromDTO(authorDTO)));
    }

    @Override
    public AuthorDTO getEntityRoutine(Integer id) throws DaoException {
        return authorToDTO(authorDao.getEntity(id));
    }

    @Override
    public AuthorDTO deleteEntityRoutine(Integer id) throws DaoException {
        return authorToDTO(authorDao.deleteEntity(id));
    }

    @Override
    public AuthorDTO getEntity(Integer id) {
        return authorToDTO(authorDao.getEntity(id));
    }

    private AuthorDTO authorToDTO(AuthorJPAEntity author) {
        return AuthorJPAToDTOMapper.INSTANCE.authorToDTO(author);
    }

    private AuthorJPAEntity authorFromDTO(AuthorDTO authorDTO) {
        return AuthorJPAFromDTOMapper.INSTANCE.authorFromDTO(authorDTO);
    }
}
