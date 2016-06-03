package com.my.lab.core.dto.converter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.dao.entity.BookJPAEntity;

import java.util.stream.Collectors;

public class BookConverter implements Converter<BookDTO, BookJPAEntity> {

    // TODO: Consider using MapStruct
    private AuthorConverter subConverter = new AuthorConverter();

    @Override
    public BookDTO convertToDTO(BookJPAEntity entity) {
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setWritten(entity.getWritten());
        dto.setGenres(entity.getGenres());
        dto.setAuthors(entity.getAuthors().stream()
                .map(subConverter::convertToDTO)
                .collect(Collectors.toList()));
        return dto;
    }

    @Override
    public BookJPAEntity convertFromDTO(BookDTO dto) {
        BookJPAEntity daoEntity = new BookJPAEntity(dto.getName());
        daoEntity.setId(dto.getId());
        daoEntity.setWritten(dto.getWritten());
        daoEntity.setGenres(dto.getGenres());
        daoEntity.setAuthors(dto.getAuthors().stream()
                .map(subConverter::convertFromDTO)
                .collect(Collectors.toList()));
        return daoEntity;
    }
}
