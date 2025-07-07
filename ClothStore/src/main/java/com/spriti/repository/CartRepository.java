package com.spriti.repository;

import com.spriti.Model.Cart;
import com.spriti.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByPerson(Person person);

    Optional<Cart> findByPersonId(int personId);
}
