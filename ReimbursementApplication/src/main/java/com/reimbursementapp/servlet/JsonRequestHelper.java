package com.reimbursementapp.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.reimbursementapp.controller.ReimbursementController;
import com.reimbursementapp.controller.UserController;

public class JsonRequestHelper {
	
	
	public static void process(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException, IOException {
		//System.out.println(request.getRequestURI());

		
		switch(request.getRequestURI()) {
		case "/ReimbursementApplication/json/reimbursements":
			ReimbursementController.findReimbursements(request, response);
			break;
		case "/ReimbursementApplication/json/update":
			ReimbursementController.updateReimbursement(request, response);
			break;
		case "/ReimbursementApplication/json/users":
			UserController.findUsers(request, response);
			break;
		case "/ReimbursementApplication/json/ticket":
			ReimbursementController.processTicket(request, response);
			break;
		default:
		
		}
		
		
	}
}
