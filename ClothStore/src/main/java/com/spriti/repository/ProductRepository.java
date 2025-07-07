package com.spriti.repository;

import com.spriti.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//    public Product findById(int id);

    public List<Product> findByCategory(String category);
}
