package com.listshow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.listshow.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
