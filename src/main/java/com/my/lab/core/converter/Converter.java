package com.my.lab.core.converter;

import com.my.lab.core.dto.DTO;

public interface Converter <T extends DTO, K> {

    T convertToDTO(K entity);
    K convertFromDTO(T dto);
}
