package com.my.lab.web.entity.mapper.frommapper;

import com.my.lab.core.dto.BookDTO;
import com.my.lab.web.entity.BookWebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorWebFromDTOMapper.class)
public interface BookWebFromDTOMapper {

    BookWebFromDTOMapper INSTANCE = Mappers.getMapper(BookWebFromDTOMapper.class);

    BookWebEntity bookFromDTO(BookDTO bookDTO);
}
