package com.rental.repository;

import com.rental.entity.BookACar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookACarRepository extends JpaRepository<BookACar,Long> {


    List<BookACar> findAllByUserId(Long userId);
}
