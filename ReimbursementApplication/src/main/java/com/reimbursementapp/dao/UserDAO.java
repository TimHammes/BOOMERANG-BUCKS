package com.reimbursementapp.dao;

import java.util.HashMap;
import java.util.List;

import com.reimbursementapp.model.User;

public interface UserDAO {
	
	void addUser(User user);
	List<User> selectAllUsers();
	User selectUserByUserName(String username);
	HashMap<String,String> selectUserNamesAndPasswords();
	
	
}
