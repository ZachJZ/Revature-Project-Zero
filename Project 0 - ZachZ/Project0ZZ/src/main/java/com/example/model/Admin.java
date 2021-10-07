package com.example.model;

public class Admin {

	private String admin_username;
	private String admin_password;
	private int ssn;
	private String admin_name;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String admin_username, String admin_password, int ssn, String admin_name) {
		super();
		this.admin_username = admin_username;
		this.admin_password = admin_password;
		this.ssn = ssn;
		this.admin_name = admin_name;
	}
	
	
	@Override
	public String toString() {
		return "Admin [admin_username=" + admin_username + ", admin_password=" + admin_password + ", ssn=" + ssn
				+ ", admin_name=" + admin_name + "]";
	}

	
	public String getAdmin_username() {
		return admin_username;
	}

	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}

	public String getAdmin_password() {
		return admin_password;
	}

	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}

	public int getSsn() {
		return ssn;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
}
