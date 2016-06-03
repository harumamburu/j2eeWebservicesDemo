package com.my.lab.core.adapter;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.DAO;
import com.my.lab.dao.db.DbAuthorDao;
import com.my.lab.dao.entity.AuthorJPAEntity;
import com.my.lab.dao.entity.mapper.frommapper.AuthorJPAFromDTOMapper;
import com.my.lab.dao.entity.mapper.tomapper.AuthorJPAToDTOMapper;

import javax.ejb.EJB;

public class AuthorAdapter implements DAO<AuthorDTO> {

    @EJB
    private DbAuthorDao authorDao;

    @Override
    public AuthorDTO getEntity(Integer id) {
        return authorToDTO(authorDao.getEntity(id));
    }

    @Override
    public AuthorDTO saveEntity(AuthorDTO authorDTO) {
        return authorToDTO(authorDao.saveEntity(authorFromDTO(authorDTO)));
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
