package com.reimbursementapp.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.reimbursementapp.controller.HomeController;
import com.reimbursementapp.controller.LoginController;
import com.reimbursementapp.controller.ReimbursementController;

public class RequestHelper {
	
	public static String process(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		//System.out.println(request.getRequestURI());
	
		
		switch(request.getRequestURI()) {
		case "/ReimbursementApplication/forwarding/login":
			return LoginController.login(request, response);
		case "/ReimbursementApplication/forwarding/home":
			return HomeController.home(request, response);
		case "/ReimbursementApplication/forwarding/ticket":
			return ReimbursementController.processTicket(request, response);
		default:
			return "/resources/html/badlogin.html";
		}
		
		
	}
	
	
}
