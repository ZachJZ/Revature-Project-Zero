package com.example.service;

import java.util.List;

import com.example.dao.DBConnection;
import com.example.dao.UserDaoImpl;
import com.example.model.User;

public class UserService {

	private DBConnection DBCon;
	private UserDaoImpl uDao;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public UserService(UserDaoImpl uDao) {
		this.uDao = uDao;
	}
	
	public UserService(DBConnection DBCon){
		this.DBCon = DBCon;
	}

	public void canInsertUserObject(User user) {
		
	}
	
	public void CanInsertUser(String username) {
		
	}
	
	//login
	boolean checkLoggedInUser(String username, String password) {
		
		return false;
	}
	
	//withdraw
	public boolean canWithdraw(String current_user, double amount) {
		//User user = uDao.getAllUsersWithAccountTypeAndName()
		User u = uDao.getByUsername(current_user);
		
		if (amount > u.getBalance()) {
			System.out.println("You cannot withdraw that much!");
			return false;
		}
		else {
			System.out.println("Withdrawing $" + amount + " from your account");
			return true;
		}
	}
	
	//transfer
	public boolean canTransfer(String current_user, double amount, String to_user) {
		
		User u = uDao.getByUsername(current_user);
		
		if (amount > u.getBalance()) {
			System.out.println("You don't have that much to transfer!");
			return false;
		}
		else {
			System.out.println("Transfering $" + amount + " from your account to " + to_user);
			return true;
		}
		
	}
	
	//USABLE
	public User getUserByName(String username) {
		
		User u = uDao.getByUsername(username);
		
		if (u.getUsername() == null) {
			throw new NullPointerException("There is not a user card with username: " + username);
		}else 
			return u;
	}
	
	
}
