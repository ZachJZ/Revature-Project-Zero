package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Person;
import com.example.model.User;

public class PersonDaoImpl implements PersonDao{

	private DBConnection DBCon;
	
	//default constructor
	public PersonDaoImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//connection setter
	public PersonDaoImpl(DBConnection DBCon) {
		this.DBCon = DBCon;
	}
	
	//DONE
	@Override
	public List<Person> getAllPersons() {
		List<Person> personList = new ArrayList<>();
		//List<User> userList = new ArrayList<>();
		
		try(Connection con = DBCon.getDBConnection()){
			
			//String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn;";
			String sql = "select first_name, last_name from persons";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				personList.add(new Person (rs.getString(1), rs.getString(2)));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personList;
	}
	
	public int getPersonsNumber(String fName, String lName) {
		Person person = new Person();
		//List<User> userList = new ArrayList<>();
		
		try(Connection con = DBCon.getDBConnection()){
			//String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn;";
			String sql = "select * from persons where first_name = '"+ fName +"' and last_name = '" + lName +"'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				person = new Person(rs.getString(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person.getUserId();
	}

	//DONE
	@Override
	public void insert_person(String fName, String lName) {
		try(Connection con = DBCon.getDBConnection()){
			String sql = "select insert_person (?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Person getPersonWithNumber(int idINT) {
		Person person = new Person();
		//List<User> userList = new ArrayList<>();
		try(Connection con = DBCon.getDBConnection()){
			//String sql = "select u.username, u.usertype, u.balance, p.first_name, p.last_name from users u left join persons p on u.ssn = p.ssn;";
			String sql = "select * from persons where ssn =" + idINT;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {				 
				person = new Person(rs.getString(1), rs.getString(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}
}
