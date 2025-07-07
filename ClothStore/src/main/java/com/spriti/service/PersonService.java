package com.spriti.service;

import com.spriti.Model.Person;

import java.util.List;

public interface PersonService {

    public Person newPerson(Person person);

    public Person getPersonDetails(String username);

    public List<Person> getAllUsers(String role);
}
