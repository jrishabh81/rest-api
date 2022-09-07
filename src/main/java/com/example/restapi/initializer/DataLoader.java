package com.example.restapi.initializer;

import com.example.restapi.hibernate.entity.PersonEntity;
import com.example.restapi.hibernate.mapper.PersonMapper;
import com.example.restapi.hibernate.model.Person;
import com.example.restapi.hibernate.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public DataLoader(PersonRepository personRepository, ObjectMapper objectMapper) {
        this.personRepository = personRepository;
        this.objectMapper = objectMapper;
    }

    @Value("classpath:data/dummy_data.json")
    private Resource resource;


    @Override
    public void run(String... args) throws Exception {
        Stream<PersonEntity> people = objectMapper
                .readValue(resource.getInputStream(), new TypeReference<List<Person>>() {
                })
                .stream()
                .map(PersonMapper.MAPPER::getEntity);

        personRepository.saveAll(people.collect(Collectors.toList()));
    }

}
