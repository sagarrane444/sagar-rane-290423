package com.avisys.cim.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avisys.cim.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	Optional<Customer> findByMobileNumber(String mobileNumber);
	
	@Query(value="select c from Customer c where c.firstName=?1 and c.lastName=?2 and c.mobileNumber=?3")
	List<Customer> getSpecific(String first, String last, String mobile);
}
