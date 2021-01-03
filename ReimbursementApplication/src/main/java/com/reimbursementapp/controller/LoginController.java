package com.reimbursementapp.controller;

import java.io.IOException;
import com.reimbursementapp.utilities.AppLogger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursementapp.model.User;
import com.reimbursementapp.service.UserService;
import com.reimbursementapp.service.UserServiceImpl;

public class LoginController {
	
	private static UserService userService = new UserServiceImpl();
	
	
	
	public static String login(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		
		HttpSession session = request.getSession();
		//System.out.println("Session Id: " + session.getId());
		
		
		if(!request.getMethod().equals("POST")) {
			return "/index.html";
		}
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		AppLogger.appLogger.info("Authenticating user...");
		
		boolean authenticated = userService.login(username, password);
		
		//System.out.println("Username: " + username + " Password: " + password);
		
		User user = userService.getUserByUserName(username);
		
		//System.out.println(user.getUserRole());	
		
		Cookie user_name = new Cookie("username", request.getParameter("username"));
		user_name.setMaxAge(-1);
		response.addCookie(user_name);
		response.setContentType("text/html");
		response.getWriter().write(new ObjectMapper().writeValueAsString(user));

		
		
		if(!authenticated) {
			AppLogger.appLogger.info("Authentication Failed For Login");
			return "/resources/html/badlogin.html";
		}else {
			
			AppLogger.appLogger.info("Authentication Was Succesful");
			AppLogger.appLogger.info("Logged In User" + username);
			if(user.getUserRole().contentEquals("employee")) {
				
				session.setAttribute("loggedusername", username);
				session.setAttribute("loggedpassword", password);
			
				response.getWriter().write(new ObjectMapper().writeValueAsString(user));
				
				return "/resources/html/employee.html";
			}else {
				
				AppLogger.appLogger.info("Authentication Was Succesful");
				AppLogger.appLogger.info("Logged In User" + username);
				session.setAttribute("loggedusername", username);
				session.setAttribute("loggedpassword", password);
			
				response.getWriter().write(new ObjectMapper().writeValueAsString(user));
				return "/resources/html/manager.html";
			}
			
		}
		
	}
	

}
