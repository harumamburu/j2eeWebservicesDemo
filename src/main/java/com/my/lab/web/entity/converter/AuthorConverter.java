package com.my.lab.web.entity.converter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.web.entity.AuthorWebEntity;

public class AuthorConverter implements Converter<AuthorDTO, AuthorWebEntity>{

    @Override
    public AuthorDTO convertToDTO(AuthorWebEntity entity) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirth(entity.getBirth());
        return dto;
    }

    @Override
    public AuthorWebEntity convertFromDTO(AuthorDTO dto) {
        AuthorWebEntity daoEntity = new AuthorWebEntity();
        daoEntity.setId(dto.getId());
        daoEntity.setName(dto.getName());
        daoEntity.setBirth(dto.getBirth());
        return daoEntity;
    }
}
