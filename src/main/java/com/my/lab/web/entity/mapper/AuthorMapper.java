package com.my.lab.web.entity.mapper;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.web.entity.AuthorWebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToDTO(AuthorWebEntity author);
}
