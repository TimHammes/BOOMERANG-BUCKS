package com.reimbursementapp.service;

import java.util.List;

import com.reimbursementapp.model.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User getUserByUserName(String username);
	
	public void createAccount(User user);
	
	public boolean checkUsernameAndPassword(String u, String p);
	
	public boolean login (String u, String p);
}
