package com.spriti.serviceImpl;

import com.spriti.Model.Person;
import com.spriti.repository.PersonRepository;
import com.spriti.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Person newPerson(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));

        return repository.save(person);
    }

    @Override
    public Person getPersonDetails(String username) {
        Person person = repository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        return person;
    }

    @Override
    public List<Person> getAllUsers(String role) {
       List<Person> userList = repository.findByRole(role);

       return userList;
    }
}
