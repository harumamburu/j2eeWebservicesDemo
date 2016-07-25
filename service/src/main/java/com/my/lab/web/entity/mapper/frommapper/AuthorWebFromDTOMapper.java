package com.my.lab.web.entity.mapper.frommapper;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.web.entity.AuthorWebEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorWebFromDTOMapper {

    AuthorWebFromDTOMapper INSTANCE = Mappers.getMapper(AuthorWebFromDTOMapper.class);

    AuthorWebEntity authorFromDTO(AuthorDTO authorDTO);
}
