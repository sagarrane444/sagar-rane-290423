package com.avisys.cim.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.Customer;
import com.avisys.cim.CustomerCreateInputDTO;
import com.avisys.cim.CustomerInputDTO;
import com.avisys.cim.MobileNumber;
import com.avisys.cim.repos.CustomerRepo;
import com.avisys.cim.repos.MobileNumbersRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private MobileNumbersRepo mobilRepo;

	@Override
	public List<Customer> getAllCustomers() {
		return custRepo.findAll();
	}

	@Override
	public Customer getSingleByMobile(String moNo) {
		MobileNumber number= mobilRepo.findByNumber(moNo).orElseThrow();
		return custRepo.findById(number.getCust().getId()).orElseThrow();
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
	public Customer getCustomerByCustomerInfo(CustomerInputDTO cust) {
		Customer customer= custRepo.getSpecific(cust.getFirstName(), cust.getLastName()).stream().findFirst().orElseThrow();
		customer.getMobileNumbers().stream().filter(m->m.getNumber().equals(cust.getMobileNumber())).findAny().orElseThrow();
		return customer;
	}

	@Override
	public Customer addNewCustomer(CustomerCreateInputDTO customer) {
		Customer cust= new Customer(customer.getFirstName(), customer.getLastName());
		cust= custRepo.save(cust);
		List<MobileNumber> numbers=new ArrayList<>();
		for(int i=0; i<customer.getNumbers().size(); i++) {
			numbers.add(new MobileNumber(customer.getNumbers().get(i), cust));
		}
		cust.setMobileNumbers(numbers);
		return cust;
	}
	
	
}
