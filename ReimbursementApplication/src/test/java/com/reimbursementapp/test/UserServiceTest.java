package com.reimbursementapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.reimbursementapp.model.User;
import com.reimbursementapp.service.UserService;
import com.reimbursementapp.service.UserServiceImpl;

public class UserServiceTest {

	private static UserService userService = new UserServiceImpl();
	

//	@Test
//	public void createUserTest() {
//		
//		int precount = userService.getAllUsers().size();
//		
//		User user = new User(0,"Gl25","Greg","Logan","gregl@gmail.com", 1);
//		
//		userService.createAccount(user);
//		
//		int postcount = userService.getAllUsers().size();
//		
//		userService.deleteAccountByUsername("Gl25");
//		
//		assertEquals(precount + 1, postcount);
//		
//		
//	}
	
	@Test
	public void loginTest() {
		
		List<User> users = userService.getAllUsers();
		String username = users.get(0).getUserName();
		String password = users.get(0).getPassword();
		System.out.println(username + " and " + password);
		
		if(users.size() > 0) {
			assertTrue(userService.login(username, password));
		}
		
	}
	
	
	@Test
	public void loginFailsFromInvalidPassword() {
		
		List<User> users = userService.getAllUsers();
		String username = users.get(0).getUserName();
		
		
		if(users.size() > 0) {
			assertTrue(!userService.login(username, "random"));
		}
		
	}
	
	
	
	@Test
	public void getUsersTest() {
		List<User> users = new ArrayList<>();
		int beforeCount = users.size();
		users = userService.getAllUsers();
		int afterCount = users.size();
		
		assertEquals(0, beforeCount);
		assertNotEquals(0, afterCount);
		
	}
	
	@Test
	public void getUserByUserNameTest() {
		
		User user = new User("Gl25","gregspassword","Greg","Logan","gregl@gmail.com", 1);
	
		userService.createAccount(user);
		User greg = userService.getUserByUserName("Gl25");
		
		assertEquals("Greg", greg.getFirstName());
		assertEquals("Logan" , greg.getLastName());
		
		
	}
	
	@Test
	public void getUserByUserNameFailsInValidUsername() {
		
		//User user = new User(0, "Gl25","Greg","Logan","toddh@gmail.com", 1);
	
		//userService.createAccount(user);
		
		try {
			User user = userService.getUserByUserName("random");
		} catch (Exception e) {
			
			fail("Username does not exist");
		}
		
		
		
		
		
		
	}
	
//	@Test
//	public void createUserWithDuplicateEmailTest() {
//		
//		int precount = userService.getAllUsers().size();
//		
//		User user = new User("Gl25","gregspassword","Greg","Logan","toddh@gmail.com", 0);
//		
//		userService.createAccount(user);
//		
//		int postcount = userService.getAllUsers().size();
//		
//		//userService.deleteAccountByUsername("Gl25");
//		
//		assertEquals(precount, postcount);
//		
//		
//	}
	
	
	
	
	

}
