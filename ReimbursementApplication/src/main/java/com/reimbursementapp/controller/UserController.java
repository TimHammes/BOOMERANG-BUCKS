package com.reimbursementapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursementapp.model.User;
import com.reimbursementapp.service.UserService;
import com.reimbursementapp.service.UserServiceImpl;
import com.reimbursementapp.utilities.AppLogger;

public class UserController {

	

	private static UserService userServ = new UserServiceImpl();
	
	
	public static void findUsers(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		AppLogger.appLogger.info("Requesting Users");
		
		HttpSession session = req.getSession();
		
		String username = req.getParameter("username");
		List<User> users = userServ.getAllUsers();
		
		AppLogger.appLogger.info("Returned Users");
		res.setContentType("text/html");
		res.getWriter().write(new ObjectMapper().writeValueAsString(users));
		
		
	
	}
}
