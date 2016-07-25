package com.my.lab.web.entity.mapper.tomapper;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.web.entity.AuthorWebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorWebToDTOMapper {

    AuthorWebToDTOMapper INSTANCE = Mappers.getMapper(AuthorWebToDTOMapper.class);

    AuthorDTO authorToDTO(AuthorWebEntity author);
}
