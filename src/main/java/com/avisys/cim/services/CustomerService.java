package com.avisys.cim.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.avisys.cim.Customer;

public interface CustomerService {

	List<Customer> getAllCustomers();

	Customer getSingleByMobile(String moNo);

	List<Customer> getCustomerByFirstName(String firstName);

	List<Customer> getCustomerByLastName(String lastName);

	Customer getCustomerByCustomerInfo(Customer cust);

	Customer addNewCustomer(Customer customer);

}
