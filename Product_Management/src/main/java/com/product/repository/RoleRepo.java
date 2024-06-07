package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.model.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
