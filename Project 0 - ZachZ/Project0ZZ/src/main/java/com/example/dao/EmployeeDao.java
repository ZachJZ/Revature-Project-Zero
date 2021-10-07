package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface EmployeeDao {

	//check unapproved users
	List<User> getUnapprovedUsers();
	
	//approve user
	void approveUser(String username);
	
	//get user info
	User getUserInfo(String username);
	
}
