package com.example.restapi.controller;

import com.example.restapi.hibernate.entity.PersonEntity;
import com.example.restapi.hibernate.mapper.PersonMapper;
import com.example.restapi.hibernate.model.Person;
import com.example.restapi.hibernate.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    private final PersonRepository personRepository;
    private final PersonMapper mapper = PersonMapper.MAPPER;

    @GetMapping("/get/{id}")
    public ResponseEntity<Person> get(@PathVariable Long id) {
        Optional<PersonEntity> personEntity = personRepository.findById(id);
        return personEntity.map(entity -> ResponseEntity.ok(mapper.getModel(entity))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Person> delete(Long id) {
        personRepository.deleteById(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/findAll2")
    public ResponseEntity<Page<Person>> findAll2() {
        Page<PersonEntity> page = personRepository.findAll(Pageable.ofSize(20));
        List<Person> personStream = page.getContent().stream().map(mapper::getModel).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(personStream, Pageable.ofSize(20), page.getTotalElements()));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<Page<Person>> findAll(Pageable pageRequest) {
        Page<PersonEntity> page = personRepository.findAll(pageRequest);
        List<Person> personStream = page.getContent().stream().map(mapper::getModel).collect(Collectors.toList());
        return ResponseEntity.ok(new PageImpl<>(personStream, pageRequest, page.getTotalElements()));
    }

    @PostMapping("/save")
    public ResponseEntity<Long> save(@RequestBody Person person) {
        if (person.getId() != null) {
            Optional<PersonEntity> byId = personRepository.findById(person.getId());
            if (byId.isPresent()) {
                PersonEntity personEntity = byId.get();
                personEntity.setId(person.getId());
                personEntity.setFirstName(person.getFirstName());
                personEntity.setLastName(person.getLastName());
                personEntity.setEmail(person.getEmail());
                personEntity.setGender(person.getGender());
                personEntity.setAvatar(person.getAvatar());
                personEntity.setCompany(person.getCompany());
                personRepository.save(personEntity);
                return ResponseEntity.ok(person.getId());
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            PersonEntity entity = mapper.getEntity(person);
            entity.setId(-1);
            personRepository.save(entity);
            return ResponseEntity.ok(entity.getId());
        }

    }
}
