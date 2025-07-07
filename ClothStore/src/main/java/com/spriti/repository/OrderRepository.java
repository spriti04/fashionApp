package com.spriti.repository;

import com.spriti.Model.Person;
import com.spriti.Model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Integer> {

    public List<ProductOrder> findByCart_Person_Id(int personId);

}
