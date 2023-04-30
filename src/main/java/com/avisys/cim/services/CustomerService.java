package com.avisys.cim.services;

import java.util.List;

import com.avisys.cim.Customer;
import com.avisys.cim.CustomerCreateInputDTO;
import com.avisys.cim.CustomerInputDTO;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer getSingleByMobile(String moNo);

	List<Customer> getCustomerByFirstName(String firstName);

	List<Customer> getCustomerByLastName(String lastName);

	Customer getCustomerByCustomerInfo(CustomerInputDTO cust);

	Customer addNewCustomer(CustomerCreateInputDTO cust);

	void deleteByNumber(String mobileNumber);

	void addMobileNumbers(Long id, List<String> numbers);

	void removeMobileNumbers(Long id, List<String> numbers);

}
