package com.reimbursementapp.service;

import java.util.List;

import com.reimbursementapp.model.Reimbursement;
import com.reimbursementapp.model.User;

public interface ReimbursementService {

	public List<Reimbursement> getAllReimbursements();
	
	public Reimbursement getReimbursementById(int id);
	
	public List<Reimbursement> getAllReimbursementsByStatusType(String status);
	
	public void createReimbursement(Reimbursement reimbursement);
	
	public void changeReimbursementStatus(int reimbID, int statusID, int resolverId);
	
	public void settleReimbursement(int id);
}
