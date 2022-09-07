package com.example.restapi.hibernate.mapper;

import com.example.restapi.hibernate.entity.PersonEntity;
import com.example.restapi.hibernate.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper MAPPER = Mappers.getMapper(PersonMapper.class);

    Person getModel(PersonEntity person);

    PersonEntity getEntity(Person person);
}
