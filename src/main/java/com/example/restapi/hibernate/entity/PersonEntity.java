package com.example.restapi.hibernate.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonEntity {
    @Id
    @Column(nullable = false)
    private long id=-1;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String avatar;
    private String company;


    public long getId() {
        return id;
    }

    public PersonEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PersonEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PersonEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PersonEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public PersonEntity setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public PersonEntity setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getCompany() {
        return company;
    }

    public PersonEntity setCompany(String company) {
        this.company = company;
        return this;
    }
}
