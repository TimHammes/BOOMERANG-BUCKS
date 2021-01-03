package com.reimbursementapp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reimbursementapp.model.Reimbursement;
import com.reimbursementapp.model.ReimbursementStatus;
import com.reimbursementapp.service.ReimbursementService;
import com.reimbursementapp.service.ReimbursementServiceImpl;
import com.reimbursementapp.service.UserService;
import com.reimbursementapp.service.UserServiceImpl;
import com.reimbursementapp.utilities.AppLogger;

public class ReimbursementController {
	
	private static ReimbursementService reimbServ = new ReimbursementServiceImpl();
	private static UserService userServ = new UserServiceImpl();
	
	
	public static void findReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		HttpSession session = req.getSession();
		
		AppLogger.appLogger.info("Requesting Reimbursements");
		List<Reimbursement> reimbursements = reimbServ.getAllReimbursements();
		AppLogger.appLogger.info("Returned Reimbursements");
		
		res.setContentType("text/html");
		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbursements));
		
		
		
		//PrintWriter out = new PrintWriter(new ObjectMapper().writeValueAsString(reimbursements));
		
		//out.println();
	
	}
	
	public static void findReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	
		
		HttpSession session = req.getSession();
		
		
		List<Reimbursement> reimbursements = reimbServ.getAllReimbursements();
		
		System.out.println(reimbursements.size());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString(reimbursements));
		
	
	
	}
	
	public static void updateReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		BufferedReader buf = req.getReader();
		String line = buf.readLine();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(line);
		
		
		String status = rootNode.get("reimb_status").toString().replaceAll("\"", "");
		int reimbId = Integer.parseInt(rootNode.get("reimb_id").toString());
		int resolverId = Integer.parseInt(rootNode.get("resolver").toString());
		int statusId = ReimbursementStatus.valueOf(status).ordinal();
	
		reimbServ.changeReimbursementStatus(reimbId, statusId, resolverId);
		
		AppLogger.appLogger.info("Ticket " + reimbId + " Status Updated" );
		AppLogger.appLogger.info("Status:" + status);
		
	}
	
	public static String processTicket(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		AppLogger.appLogger.info("Processing New Ticket");
		if(!req.getMethod().equals("POST")) {
			return "/employee.html";
		}
	
		String type = req.getParameter("reimb-type");
		double amt = Double.parseDouble(req.getParameter("reimb-amount"));
		BigDecimal amount = BigDecimal.valueOf(amt);
		String desc = req.getParameter("reim-desc");
		int userId = Integer.parseInt(req.getParameter("userId")) ;
		
		Reimbursement reimbursement = new Reimbursement(amount, desc, userId, type);
		
		reimbServ.createReimbursement(reimbursement);
		
		AppLogger.appLogger.info("New Ticket Created");
		AppLogger.appLogger.info("Status:Pending");
		
		return "/resources/html/employee.html";
	}
	

	
	
}
