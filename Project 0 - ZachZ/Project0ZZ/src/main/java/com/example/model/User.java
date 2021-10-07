package com.example.model;

public class User {

	private String username;
	private String password;
	private int ssn;
	private double balance;
	private String user_type;
	private String person_name;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", balance=" + balance + ", user_type="
				+ user_type + "]";
	}
	
	//user with full constructor
	public User(String username, String password, double balance, String user_type) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.user_type = user_type;
	}
	
	//user with no password
	public User(String username, double balance, String user_type) {
		super();
		this.username = username;
		this.balance = balance;
		this.user_type = user_type;
	}
	
	//user with no password and with name
	public User(String username, double balance, String user_type, String personName) {
		super();
		this.username = username;
		this.balance = balance;
		this.user_type = user_type;
		this.person_name = personName;
	}
	
	//get full plus pass
	public User(String username, String password, String user_type, double balance, String personName, int ssn) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.user_type = user_type;
		this.person_name = personName;
		this.ssn = ssn;
	}
	
	//just the username and user type
	public User(String username,  String user_type) {
		super();
		this.username = username;
		this.user_type = user_type;
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

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getPerson_name() {
		return person_name;
	}

	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	
	
	
}
