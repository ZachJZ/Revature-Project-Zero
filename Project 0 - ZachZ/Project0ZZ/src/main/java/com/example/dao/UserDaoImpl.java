package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.model.Person;
import com.example.model.User;
import com.example.service.UserService;

public class UserDaoImpl implements UserDao{ 
	
	private DBConnection DBCon;
	//private UserService uServ;
	
	//default constructor
	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//connection setter
	public UserDaoImpl(DBConnection DBCon) {
		this.DBCon = DBCon;
	}
	
	public User getByUsername(String username) {
		User user = null;
		
		//SELECT * FROM users u LEFT JOIN persons p ON u.ssn = p.ssn WHERE u.username = 'LowKeyDoc';
		try(Connection con = DBCon.getDBConnection()){
			//String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn";
			String sql = "SELECT * FROM users u LEFT JOIN persons p ON u.ssn = p.ssn WHERE u.username = '"+ username +"'";
			PreparedStatement ps = con.prepareStatement(sql);			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				//username pass type balance ssn first last ssn		
				//name balance type person
				user = new User(rs.getString(1), rs.getDouble(5), rs.getString(3), rs.getString(6) + "" + rs.getString(7));
				//userList.add(new User (rs.getString(1), rs.getDouble(3), rs.getString(2)));
			}
			else {
				throw new NullPointerException("There is no user with \"" + username + "\" as their username");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//WORKS
	public List<User> getAllUsers(){
		
		//select username, usertype, balance from users;
		List<User> userList = new ArrayList<>();
		try(Connection con = DBCon.getDBConnection()){
			
			//String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn;";
			String sql = "select username, usertype, balance from users";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				//name type balance first last		
				userList.add(new User (rs.getString(1), rs.getDouble(3), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
		
	}

	
	//WORKS
	public List<User> getAllUsersWithAccountTypeAndName(){
		
		List<User> userList = new ArrayList<>();
		
		//WORKS
		//select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn;
		try(Connection con = DBCon.getDBConnection()){
			
			//String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn;";
			String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				//name type balance first last		
				userList.add(new User (rs.getString(1), rs.getDouble(3), rs.getString(2), rs.getString(4) + " " + rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	

	//WORKS
	public boolean insertUserObject(User user) {
		User u = getByUsername(user.getUsername());
		if (u.equals(null)) {
			try(Connection con = DBCon.getDBConnection()){
				String sql = "select insert_user (?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setInt(3, user.getSsn());
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			System.out.println("Username" + user.getUsername() +" is already in use");
			return false;
		}	
	}
	
	
	//WORKS
	public boolean insertUser(String username, String password, int ssn) {
		//User u = getByUsername(username);
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select insert_user (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setInt(3, ssn);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	//login
	//WORKS
	public boolean checkLoggedInUser(String username, String password) {
		//select username, password from users u where u.username like '?';
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select username, password from users u where u.username like '" + username + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				if (password.equals(rs.getString(2)))
					{
						System.out.println("Login of " + username + " success!");
						return true;
					}
				else {
					System.out.println("Login of " + username + " failed. Wrong password");
					return false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Login of " + username + " failed. That user does not match that password. ");
		return false;
	}
	
	
	//withdraw
	//WORKS
	public boolean withdraw(String current_user, double amount) {
		
		User u = getByUsername(current_user);
		//System.out.println(u);
		if (amount > u.getBalance()) {
			System.out.println("You cannot withdraw that much!");
			return false;
		}
		else {
			//select withdraw('NormalMan', 100);
			try(Connection con = DBCon.getDBConnection()){
				String sql = "select withdraw(?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, current_user);
				ps.setDouble(2, amount);
				ps.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Withdrawing $" + amount + " from your account");
			return true;
		}
	}
	
	
	//deposit
	//WORKS
	public boolean deposit(String current_user, double amount) {

		if (amount > 0) {
			//select deposit('NormalMan', 100);
			try(Connection con = DBCon.getDBConnection()){
				String sql = "select deposit(?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, current_user);
				ps.setDouble(2, amount);
				ps.execute();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			System.out.println(amount + " is not a valid amount to deposit. You can only deposit an amount above $0.");
			return false;
		}
	}
	
	//balance
	public double balance(String current_user) {
		
		double returnInt = -1337;
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select balance from users where username = '" + current_user + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				returnInt = rs.getDouble(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnInt;
	}
	
	//transfer
	
	//WORKS
	public boolean transfer(String current_user, double amount, String to_username) {
		//select transfer('ZachZum', 100, 'ZachTwo');
		User u = getByUsername(current_user);
		if (amount > u.getBalance()) {
			System.out.println("You cannot transfer that much!");
			return false;
		}
		else if (amount <= 0) {
			System.out.println("You cannot transfer an amount below 0");
			return false;
		}
		else {
			try(Connection con = DBCon.getDBConnection()){
				String sql = "select transfer(?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, current_user);
				ps.setDouble(2, amount);
				ps.setString(3, to_username);
				ps.execute();
				System.out.println("Successful transfer");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
}
