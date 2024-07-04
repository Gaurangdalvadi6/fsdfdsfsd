package com.graphql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graphql.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{

}
