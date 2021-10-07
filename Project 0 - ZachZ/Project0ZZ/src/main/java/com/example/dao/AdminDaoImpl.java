package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.User;


public class AdminDaoImpl implements AdminDao{

	private DBConnection DBCon;
	
	public AdminDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public AdminDaoImpl(DBConnection DBCon) {
		this.DBCon = DBCon;
	}

	@Override
	public void updateUser(String username, String update_to) {
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select update_user_type(?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, update_to);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Updated user " + username + " to " + update_to);

	}

	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		//select delete_user ('DrK');
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select delete_user(?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Deleted user " + username);
	}
	
	public List<User> getUnapprovedUsers() {
		//select username, usertype from users where usertype = 'Unapproved'
		List<User> userList = new ArrayList<>();
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select username, usertype from users where usertype = 'Unapproved'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//ps.execute();
			
			while (rs.next()) {				 
				//name type balance first last		
				userList.add(new User (rs.getString(1), rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public void approveUser(String username) {
		//select approve_user('IronMan')
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select approve_user(?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Approved user " + username);
	}

	@Override
	public void promoteUser(String username) {
		//select promote_user('ZachZum');
		try(Connection con = DBCon.getDBConnection()){
			
			String sql = "select promote_user(?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Promoted user " + username);
	}

	//sensative
	@Override
	public List<User> getAllUserInfo() {
		List<User> userList = new ArrayList<>();
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select * from users u left join persons p on u.ssn = p.ssn";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				//user pass type balance //ssn first + last //ssn
				//string x 3 double string + string
				userList.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getString(6) + " " + rs.getString(7), rs.getInt(8)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	//Regular
	@Override
	public User getThisUserInfo(String username) {
		User user = null;
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select username, balance, usertype from users u where u.username like " + username.toString() + "";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			//ps.execute();
			
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

	@Override
	public List<User> getSensativeUserInfo() {
		// TODO Auto-generated method stub
		//select * from users u left join persons p on u.ssn = p.ssn;
		List<User> userList = new ArrayList<>();
		try(Connection con = DBCon.getDBConnection()){
			//user pass type ssn bal first last ssn
			String sql = "select * from users u left join persons p on u.ssn = p.ssn";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			//ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				//user pass usertype balance first last
				userList.add(new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getString(6) + " " + rs.getString(7), rs.getInt(8)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User getThisUserSensativeInfo(String username) {
		// TODO Auto-generated method stub
		//select * from users u left join persons p on u.ssn = p.ssn where u.username = 'LowKeyDoc';
		User user = null;
		try(Connection con = DBCon.getDBConnection()){
			//user pass type ssn bal first last ssn
			String sql = "select * from users u left join persons p on u.ssn = p.ssn where u.username = '"+ username +"'";
			PreparedStatement ps = con.prepareStatement(sql);
			//ps.setString(1, username);
			//ps.execute();
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {	 
				//user pass usertype balance ssn first last ssn
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(5), rs.getString(6) + " " + rs.getString(7), rs.getInt(8));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
