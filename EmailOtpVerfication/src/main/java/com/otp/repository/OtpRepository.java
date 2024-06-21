package com.otp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.otp.model.Otp;


@Repository
public interface OtpRepository extends JpaRepository<Otp, Long>{

	Otp findByEmailAndOtp(String email, String otp);
}
