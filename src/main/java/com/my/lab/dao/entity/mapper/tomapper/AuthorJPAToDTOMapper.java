package com.my.lab.dao.entity.mapper.tomapper;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.entity.AuthorJPAEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorJPAToDTOMapper {

    AuthorJPAToDTOMapper INSTANCE = Mappers.getMapper(AuthorJPAToDTOMapper.class);

    AuthorDTO authorToDTO(AuthorJPAEntity author);
}
