package com.avisys.cim.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avisys.cim.MobileNumber;

public interface MobileNumbersRepo extends JpaRepository<MobileNumber, String> {
	Optional<MobileNumber> findByNumber(String number);

}
