package com.my.lab.dao.entity.mapper.frommapper;

import com.my.lab.core.dto.BookDTO;
import com.my.lab.dao.entity.BookJPAEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorJPAFromDTOMapper.class)
public interface BookJPAFromDTOMapper {

    BookJPAFromDTOMapper INSTANCE = Mappers.getMapper(BookJPAFromDTOMapper.class);

    BookJPAEntity bookFromDTO(BookDTO book);
}
