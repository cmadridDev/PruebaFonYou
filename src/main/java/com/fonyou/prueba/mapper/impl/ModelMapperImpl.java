package com.fonyou.prueba.mapper.impl;

import com.fonyou.prueba.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperImpl implements Mapper {

    private final ModelMapper modelMapper;

    public ModelMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public <D> D mapToDto(Object entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    @Override
    public <E> E mapToEntity(Object dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}
