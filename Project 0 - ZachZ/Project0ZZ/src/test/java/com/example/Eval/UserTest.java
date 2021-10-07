package com.example.Eval;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.PersonDaoImpl;
import com.example.dao.UserDaoImpl;
import com.example.model.Person;
import com.example.model.User;
import com.example.service.UserService;

public class UserTest {

	@Mock
	private UserDaoImpl uDao;
	
	private UserService uServ;

	private User testCustomer;

	
	@BeforeEach 
	public void setUp() throws Exception{
		 
		MockitoAnnotations.initMocks(this); // this will create the mocked instance of the classes that are marked with the @Mock annotation
		uServ = new UserService(uDao);
		testCustomer = new User("testUsername", 100.21, "Customer", "Jake Smith");
		when(uDao.getByUsername("testUsername")).thenReturn(testCustomer); //we are controlling output of the getByName method and the logic of that method will never execute

	}
	
	@Test
	public void testGetByUsernameSuccess() {
		assertEquals(uServ.getUserByName("testUsername"), testCustomer);
		
	}
	
	@Test
	public void testGetByUsernameFailure() {
		assertThrows(NullPointerException.class, ()-> uServ.getUserByName("asdasdf"));
		
	}
	
}
