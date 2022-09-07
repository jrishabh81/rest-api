package com.example.restapi.hibernate.repository;

import com.example.restapi.hibernate.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "person-repo")
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
