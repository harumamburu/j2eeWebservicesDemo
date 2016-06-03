package com.my.lab.dao.entity.mapper.tomapper;

import com.my.lab.core.dto.BookDTO;
import com.my.lab.dao.entity.BookJPAEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorJPAToDTOMapper.class)
public interface BookJPAToDTOMapper {

    BookJPAToDTOMapper INSTANCE = Mappers.getMapper(BookJPAToDTOMapper.class);

    BookDTO bookToDTO(BookJPAEntity book);
}
