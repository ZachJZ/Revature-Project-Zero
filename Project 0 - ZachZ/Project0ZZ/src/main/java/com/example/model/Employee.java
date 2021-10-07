package com.example.model;

public class Employee {

	private String username;
	private String password;
	private int ssn;
	private String employee_name;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(String username, String password, int ssn, String employee_name) {
		super();
		this.username = username;
		this.password = password;
		this.ssn = ssn;
		this.employee_name = employee_name;
	}
	
	
 
	@Override
	public String toString() {
		return "Employee [username=" + username + ", employee_name=" + employee_name + "]";
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getSsn() {
		return ssn;
	}
	
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	
	public String getEmployee_name() {
		return employee_name;
	}
	
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	
}
