package com.reimbursementapp.dao;

import java.util.List;

import com.reimbursementapp.model.Reimbursement;

public interface ReimbursementDAO {

		void addReimbursement(Reimbursement reimb);
		Reimbursement selectReimbursementById(int id);
		List<Reimbursement> selectAllReimbursements();
		List<Reimbursement> selectAllReimbursementsByStatusType(String status);
		void updateReimbursementStatus(int reimbID, int statusId, int resolverId);
		
}
