package com.example.dao;

import java.util.List;
import com.example.model.User;

public interface UserDao{
	
	List<User> getAllUsers();
	List<User> getAllUsersWithAccountTypeAndName();

	boolean insertUserObject(User user);
	boolean insertUser(String username, String password, int ssn);
	
	//login
	boolean checkLoggedInUser(String username, String password);
	//withdraw
	boolean withdraw(String current_user, double amount);
	//deposit
	boolean deposit(String current_user, double amount);
	//transfer
	boolean transfer(String current_user, double amount, String to_username);
}