package com.my.lab.dao.entity.converter;

import com.my.lab.core.converter.Converter;
import com.my.lab.core.dto.BookDTO;
import com.my.lab.dao.entity.Book;

import java.util.stream.Collectors;

public class BookConverter implements Converter<BookDTO, Book> {

    // TODO: Consuder using MapStruct
    private AuthorConverter subConverter = new AuthorConverter();

    @Override
    public BookDTO convertToDTO(Book entity) {
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
    public Book convertFromDTO(BookDTO dto) {
        Book daoEntity = new Book(dto.getName());
        daoEntity.setId(dto.getId());
        daoEntity.setWritten(dto.getWritten());
        daoEntity.setGenres(dto.getGenres());
        daoEntity.setAuthors(dto.getAuthors().stream()
                .map(subConverter::convertFromDTO)
                .collect(Collectors.toList()));
        return daoEntity;
    }
}
