package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.User;
import com.example.service.UserService;

public class EmployeeDaoImpl implements EmployeeDao{

	private DBConnection DBCon;
	private UserService uServ;
	
	public EmployeeDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//connection setter
	public EmployeeDaoImpl(DBConnection DBCon) {
		this.DBCon = DBCon;
	}
	
	public List<User> getUnapprovedUsers() {
		//select username, usertype from users where usertype = 'Unapproved'
		List<User> userList = new ArrayList<>();
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select username, usertype from users where usertype = 'Unapproved'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				//name type balance first last
				userList.add(new User (rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (userList.get(0) == null) {
			System.out.println("No users are pending approval.");
		}
		
		return userList;
	}

	@Override
	public void approveUser(String username) {
		// TODO Auto-generated method stub
		//select approve_user('IronMan')
		
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select approve_user(?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUserInfo(String username) {
		// TODO Auto-generated method stub
		User user = null;
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select username, balance, usertype from users u where u.username like ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				//user balance usertype
				user = new User(rs.getString(1), rs.getDouble(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
