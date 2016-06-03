package com.my.lab.dao.entity.mapper.frommapper;

import com.my.lab.core.dto.AuthorDTO;
import com.my.lab.dao.entity.AuthorJPAEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorJPAFromDTOMapper {

    AuthorJPAFromDTOMapper INSTANCE = Mappers.getMapper(AuthorJPAFromDTOMapper.class);

    AuthorJPAEntity authorFromDTO(AuthorDTO author);
}
