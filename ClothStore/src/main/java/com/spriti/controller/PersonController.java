package com.spriti.controller;

import com.spriti.Model.*;
import com.spriti.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/index")
    public String hello(){
        return "hello world";
    }

    @PostMapping("/newPerson")
    public Person createNewPerson(@RequestBody Person person){
        return personService.newPerson(person);
    }

    @GetMapping("/admin/allUsers/{role}")
    public ResponseEntity<List<Person>> allCustomers(@PathVariable String role){
        return ResponseEntity.ok(personService.getAllUsers(role));
    }
    @GetMapping("/signIn")
    public ResponseEntity<String> loggedInHandler(Authentication auth){
        System.out.println(auth);
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.ok("Please Login with your Username and Password");
        }

        Person person = personService.getPersonDetails(auth.getName());

        return ResponseEntity.ok(person.getName() + " Logged in successfully");
    }




}
