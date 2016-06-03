package com.my.lab.web.entity.mapper;

import com.my.lab.core.dto.BookDTO;
import com.my.lab.web.entity.BookWebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = AuthorMapper.class)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToDTO(BookWebEntity book);
}
