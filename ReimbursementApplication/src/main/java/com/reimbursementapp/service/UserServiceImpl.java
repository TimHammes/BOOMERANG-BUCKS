package com.reimbursementapp.service;

import java.util.HashMap;
import java.util.List;

import com.reimbursementapp.dao.UserDAO;
import com.reimbursementapp.dao.UserDAOimpl;
import com.reimbursementapp.model.User;

public class UserServiceImpl implements UserService {
	
	private static UserDAO userDAO = new UserDAOimpl();

	@Override
	public List<User> getAllUsers() {
		
		return userDAO.selectAllUsers();
	}

	@Override
	public User getUserByUserName(String username) {
		
		return userDAO.selectUserByUserName(username);
	}

	@Override
	public void createAccount(User user) {
		
		userDAO.addUser(user);

	}

	
	@Override
	public boolean checkUsernameAndPassword(String u, String p) {
		System.out.println("checking Username and Password");

		HashMap<String,String> credentials = userDAO.selectUserNamesAndPasswords();
		
		System.out.println(u +"=" + credentials.get(u));
		
		if(credentials.get(u).contentEquals(p)){
			return true;
			
		}else {
			return false;
		}
		
	}

	@Override
	public boolean login(String u, String p) {
		
		
		return checkUsernameAndPassword(u,p);
	}

	
}
