package com.movieflix.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.movieflix.auth.entity.ForgetPassword;
import com.movieflix.auth.entity.User;

@Repository
public interface ForgetPasswordRepository extends JpaRepository<ForgetPassword, Integer>{

	@Query("select fp from ForgetPassword fp where fp.otp = ?1 and fp.user = ?2")
	Optional<ForgetPassword> findByOtpAndUser(Integer otp,User user);
}
