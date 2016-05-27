package com.my.lab.core.converters.webconverter;

import com.my.lab.core.converters.Converter;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.web.entity.Author;

public class AuthorConverter implements Converter<AuthorDTO, Author>{

    @Override
    public AuthorDTO convertToDTO(Author entity) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(entity.getAuthorId());
        dto.setName(entity.getName());
        dto.setBirth(entity.getBirth());
        return dto;
    }

    @Override
    public Author convertFromDTO(AuthorDTO dto) {
        Author daoEntity = new Author();
        daoEntity.setAuthorId(dto.getId());
        daoEntity.setName(dto.getName());
        daoEntity.setBirth(dto.getBirth());
        return daoEntity;
    }
}
