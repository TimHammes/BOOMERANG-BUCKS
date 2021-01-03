package com.reimbursementapp.main;

import java.util.List;

import com.reimbursementapp.dao.ReimbursementDAO;
import com.reimbursementapp.dao.ReimbursementDAOimpl;
import com.reimbursementapp.dao.UserDAO;
import com.reimbursementapp.dao.UserDAOimpl;
import com.reimbursementapp.model.Reimbursement;
import com.reimbursementapp.model.User;
import com.reimbursementapp.service.ReimbursementService;
import com.reimbursementapp.service.ReimbursementServiceImpl;
import com.reimbursementapp.service.UserService;
import com.reimbursementapp.service.UserServiceImpl;

public class MainDriver {

	public static String URL = "jdbc:postgresql://localhost:5432/" + System.getenv("Training_DB");
	private static final String USERNAME = System.getenv("Training_DB_Username");
	private static final String PASSWORD = System.getenv("Training_DB_Password");
	
	public static void main(String[] args) {
		
		
		UserDAO userDao = new UserDAOimpl();
		ReimbursementDAO reimbDao = new ReimbursementDAOimpl();
		UserService userService = new UserServiceImpl();
		ReimbursementService reimbService = new ReimbursementServiceImpl();
		
		
		List<Reimbursement>reimbursements = reimbService.getAllReimbursements();
		
		for (Reimbursement reimbursement : reimbursements) {
			System.out.println(reimbursement);
		
		}
		
		//reimbService.changeReimbursementStatus(1, 1);
		//User user = new User("Gl25","gregspassword","Greg","Logan","gregl@gmail.com", 0);
		
		//userService.createAccount(user);
		//User user2 = userService.getUserById(1);
		
		//System.out.println(user2);
		
		//VALUES(DEFAULT, 34.56, '2016-06-22 19:10:25-07', null,'gas',null,3, 1, 0, 1);
		//Reimbursement reimb = new Reimbursement(0,193.39, "2016-06-22 19:10:25", "2016-06-22 19:10:25", "room service",null, 4,1,1,0);
		//userDao.addUser(user);
		
		//User user = userDao.selectUserById(1);
		List<User> users = userService.getAllUsers();
		
		//System.out.println(user.toString());
		
		for (User person : users) {
			System.out.println(person);
		}
//		
//		boolean isValid = userService.login("Td45", "toddspassword");
//		System.out.println(isValid);
		
		//userDao.updateUserPassword("Hr50", "hanksnewpassword");
		
		//userDao.deleteUser(5);
		
		//reimbDao.addReimbursement(reimb);
		//reimbDao.selectReimbursementById(2);
		//System.out.println(myReimb);
		
		
	}

}
