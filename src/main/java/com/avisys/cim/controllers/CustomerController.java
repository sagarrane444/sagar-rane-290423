package com.avisys.cim.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avisys.cim.Customer;
import com.avisys.cim.services.CustomerService;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService custService;

	public CustomerController() {
		System.out.println("In ctor of Controller");
	}

	//Retrieve all customers
	@GetMapping 
	ResponseEntity<List<Customer>> getAll(){
		return new ResponseEntity<>(HttpStatus.OK).ok(custService.getAllCustomers());
	}

	//Retrieve customer by only mobile number
	@GetMapping("/mobile/{moNo}")
	ResponseEntity<?> getByMobile(@PathVariable String moNo ) {

		try {
			Customer cust= custService.getSingleByMobile(moNo);
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);
		}catch(NoSuchElementException e) {
			return new ResponseEntity<String>("No Such Customer Found, Please enter valid Mobile Number.",HttpStatus.NOT_FOUND);
		}
	}

	//Retrieve customer by only first name
	@GetMapping("/firstname/{firstName}")
	ResponseEntity<?> getByFirstName(@PathVariable String firstName){

		List<Customer> custList = custService.getCustomerByFirstName(firstName);
		if(!custList.isEmpty()) {
			return new ResponseEntity<List<Customer>>(custList,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No Such Customer Found,Please Enteer Valid Customer Name.",HttpStatus.NOT_FOUND);
		}
	}

	//Retrieve customer by only last name
	@GetMapping("/lastname/{lastName}")
	ResponseEntity<?>getByLastName(@PathVariable String  lastName){
		List<Customer> cust =custService.getCustomerByLastName(lastName);
		if(!cust.isEmpty()) {
			return new ResponseEntity<List<Customer>>(cust,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("No Such Customer Found,Please Enteer Valid Customer Name.",HttpStatus.NOT_FOUND);
		}
	}

	//Retrieve customer by first name, last name and mobile number
	@PostMapping("/customerinfo")
	ResponseEntity<?> getByCustomerInfo(@RequestBody Customer cust){
		try {
			if(cust!=null && cust.getFirstName()!=null && cust.getLastName()!=null && cust.getMobileNumber()!=null) {
				Customer customer = custService.getCustomerByCustomerInfo(cust);

				return new ResponseEntity<Customer>(customer, HttpStatus.OK);
			}
			else {
				throw new NoSuchElementException();
			}

		}catch(NoSuchElementException e) {
			return new ResponseEntity<String>("No Such Customer Found,Please Enteer Valid Customer Info.",HttpStatus.NOT_FOUND);			
		}
	}
	
	//Create new Customer
	@PostMapping("/create")
	ResponseEntity<?> createNewCustomer(@RequestBody Customer customer){
		
		//tracer
		System.out.println("In Controller "+customer.getFirstName()+", "+customer.getMobileNumber());
		
		Customer newCustomer = custService.addNewCustomer(customer);
		if(newCustomer!=null)
			return new ResponseEntity<Customer>(newCustomer,HttpStatus.OK);	
		else{
			return new ResponseEntity<String>("Unable to create Customer. Mobile number already present",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
