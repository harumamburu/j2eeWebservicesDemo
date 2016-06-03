package com.my.lab.web.entity.mapper;

import com.my.lab.core.dto.BookDTO;
import com.my.lab.web.entity.BookWebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorWebToDTOMapper.class)
public interface BookWebToDTOMapper {

    BookWebToDTOMapper INSTANCE = Mappers.getMapper(BookWebToDTOMapper.class);

    BookDTO bookToDTO(BookWebEntity book);
}
