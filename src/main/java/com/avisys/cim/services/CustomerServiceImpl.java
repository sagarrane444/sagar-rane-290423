package com.avisys.cim.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.repos.CustomerRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo custRepo;

	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public Customer getSingleByMobile(String moNo) {
		return custRepo.findByMobileNumber(moNo).orElseThrow();
	}

	@Override
	public List<Customer> getCustomerByFirstName(String firstName) {
		
		List<Customer> list= custRepo.findAll();
		return list.stream().filter(c->c.getFirstName().toLowerCase().contains(firstName.toLowerCase())).collect(Collectors.toList());
	}

	@Override
	public List<Customer> getCustomerByLastName(String lastName) {
		List<Customer> list= custRepo.findAll();
		return list.stream().filter(c->c.getLastName().toLowerCase().contains(lastName.toLowerCase())).collect(Collectors.toList());
	}

	@Override
	public Customer getCustomerByCustomerInfo(Customer cust) {
		return custRepo.getSpecific(cust.getFirstName(), cust.getLastName(), cust.getMobileNumber()).stream().findFirst().orElseThrow();
	}

	@Override
	public Customer addNewCustomer(Customer customer) {
		System.out.println("In Customer Service");
		try {
			Customer oldCustomer = custRepo.findByMobileNumber(customer.getMobileNumber()).orElseThrow();
			return null;
		}catch(NoSuchElementException e) {
			System.out.println("In catch block");
			return custRepo.save(customer);
		}
	}
	
	
}
