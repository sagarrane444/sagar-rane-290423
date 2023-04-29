package com.avisys.cim;

import java.util.ArrayList;
import java.util.List;

public class CustomerCreateInputDTO {
	private String firstName;
	private String lastName;
	List<String> numbers= new ArrayList<>();
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
	
}
