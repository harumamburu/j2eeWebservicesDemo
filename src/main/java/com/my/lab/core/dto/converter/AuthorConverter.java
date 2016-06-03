package com.my.lab.core.dto.converter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.entity.AuthorJPAEntity;

public class AuthorConverter implements Converter<AuthorDTO, AuthorJPAEntity> {

    @Override
    public AuthorDTO convertToDTO(AuthorJPAEntity entity) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirth(entity.getBirth());
        return dto;
    }

    @Override
    public AuthorJPAEntity convertFromDTO(AuthorDTO dto) {
        AuthorJPAEntity daoEntity = new AuthorJPAEntity(dto.getName());
        daoEntity.setId(dto.getId());
        daoEntity.setBirth(dto.getBirth());
        return daoEntity;
    }
}
