package com.my.lab.core.converters;

import com.my.lab.core.dto.DTO;

public interface Converter <T, K extends DTO> {

    K convertToDTO(T entity);
    T convertFromDTO(K dto);
}
