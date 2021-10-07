package com.example.model;

public class Person {

	private String firstName;
	private String lastName;
	private int userId; //ssn
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", userId=" + userId + "]";
	}
	
	public Person(String firstName, String lastName, int userId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userId = userId;
	}
	
	//no id constructor
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

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

	public int getUserId() {
		return userId;
	}
	
	
	
}
