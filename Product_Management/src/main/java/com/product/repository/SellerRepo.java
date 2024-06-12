package com.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Seller;

@Repository
public interface SellerRepo extends JpaRepository<Seller, Long>{

	Optional<Seller> findByEmail(String email);

	void deleteAllById(Seller seller);

	void deleteById(Seller seller);
}
