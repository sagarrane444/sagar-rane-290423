package com.avisys.cim;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mobile_numbers")
public class MobileNumber {
	@Id
	@Column(name = "MOBILE_NUMBER", unique = true, nullable = false)
	private String number;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "cust_id")
	private Customer cust;
	
	public MobileNumber() {
		// TODO Auto-generated constructor stub
	}

	public MobileNumber(String number, Customer cust) {
		super();
		this.number = number;
		this.cust = cust;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}
	
	

}
