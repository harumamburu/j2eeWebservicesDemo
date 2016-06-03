package com.my.lab.web.entity.converter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.web.entity.BookWebEntity;

import java.util.stream.Collectors;

public class BookConverter implements Converter<BookDTO, BookWebEntity> {

    private AuthorConverter subConverter = new AuthorConverter();

    @Override
    public BookDTO convertToDTO(BookWebEntity entity) {
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
    public BookWebEntity convertFromDTO(BookDTO dto) {
        BookWebEntity daoEntity = new BookWebEntity();
        daoEntity.setId(dto.getId());
        daoEntity.setName(dto.getName());
        daoEntity.setWritten(dto.getWritten());
        daoEntity.setGenres(dto.getGenres());
        daoEntity.setAuthors(dto.getAuthors().stream()
                .map(subConverter::convertFromDTO)
                .collect(Collectors.toList()));
        return daoEntity;
    }
}
