package com.reimbursementapp.service;

import java.util.List;

import com.reimbursementapp.dao.ReimbursementDAO;
import com.reimbursementapp.dao.ReimbursementDAOimpl;
import com.reimbursementapp.model.Reimbursement;
import com.reimbursementapp.utilities.AppLogger;

	

public class ReimbursementServiceImpl implements ReimbursementService {
	
	
	
	private static ReimbursementDAO reimbDao = new ReimbursementDAOimpl();

	@Override
	public List<Reimbursement> getAllReimbursements() {
		
		List<Reimbursement> reimbursements = reimbDao.selectAllReimbursements();
		return reimbursements;
	}

	@Override
	public Reimbursement getReimbursementById(int id) {
		
		return reimbDao.selectReimbursementById(id);
	}

	@Override
	public List<Reimbursement> getAllReimbursementsByStatusType(String status){
		List<Reimbursement> reimbursements = reimbDao.selectAllReimbursementsByStatusType(status);
		return reimbursements;
	}

	
	@Override
	public void createReimbursement(Reimbursement reimbursement) {
		
		reimbDao.addReimbursement(reimbursement);
		
	}

	@Override
	public void changeReimbursementStatus(int reimbID, int statusId, int resolverId) {
		System.out.println("inside service update method");
		reimbDao.updateReimbursementStatus(reimbID, statusId, resolverId);

	}

	@Override
	public void settleReimbursement(int id) {
		// TODO Auto-generated method stub

	}



}
