package com.fonyou.prueba.mapper;

public interface Mapper {

    <D> D mapToDto(Object entity, Class<D> dtoClass);

    <E> E mapToEntity(Object dto, Class<E> entityClass);

}