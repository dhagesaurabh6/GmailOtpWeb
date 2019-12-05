package com.example.demo.otp;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface otpRepository extends JpaRepository<OtpValidationEntity, Integer> {

	
	List<OtpValidationEntity> findAll();

	

	
}
