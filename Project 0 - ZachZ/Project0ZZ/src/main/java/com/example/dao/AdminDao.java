package com.example.dao;

import java.util.List;

import com.example.model.User;

public interface AdminDao {
	
	//update user
	void updateUser(String username, String update_to);
	
	//delete user
	void deleteUser(String username);
	
	//approve user
	void approveUser(String username);
	
	//promote user
	void promoteUser(String username);
	
	//get user info
	List<User> getAllUserInfo();
	
	User getThisUserInfo(String username);
	
	//get sensative user info
	List<User> getSensativeUserInfo();

	User getThisUserSensativeInfo(String username);;
}
