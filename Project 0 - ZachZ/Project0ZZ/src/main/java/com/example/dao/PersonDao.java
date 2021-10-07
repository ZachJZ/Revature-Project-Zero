package com.example.dao;

import java.util.List;

import com.example.model.Person;

public interface PersonDao {

	List<Person> getAllPersons();
	void insert_person(String fName, String lName);
	
}
