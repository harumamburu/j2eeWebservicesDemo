package com.my.lab.core.converters.daoconverter;

import com.my.lab.core.converters.Converter;
import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.entity.Author;

public class AuthorConverter implements Converter<AuthorDTO, Author> {

    @Override
    public AuthorDTO convertToDTO(Author entity) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setBirth(entity.getBirth());
        return dto;
    }

    @Override
    public Author convertFromDTO(AuthorDTO dto) {
        Author daoEntity = new Author(dto.getName());
        daoEntity.setId(dto.getId());
        daoEntity.setBirth(dto.getBirth());
        return daoEntity;
    }
}
