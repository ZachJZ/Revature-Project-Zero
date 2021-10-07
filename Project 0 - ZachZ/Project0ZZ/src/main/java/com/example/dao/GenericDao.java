package com.example.dao;

import java.util.List;

public interface GenericDao <T> { //to create a generic interface we use <T>

	List<T> getAll();
	T getByName(String name);
	void update(T entity);
	void insert(T entity);
	void delete(T entity);
	
	
}
