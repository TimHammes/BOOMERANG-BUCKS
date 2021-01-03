package com.reimbursementapp.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.reimbursementapp.model.Reimbursement;
import com.reimbursementapp.model.ReimbursementStatus;
import com.reimbursementapp.service.ReimbursementService;
import com.reimbursementapp.service.ReimbursementServiceImpl;

public class ReimbursementServiceTest {

	private static ReimbursementService reimbServ = new ReimbursementServiceImpl();
	
	@Test
	public void createNewReimbursementTest() {
		
		List<Reimbursement> reimbsBefore = reimbServ.getAllReimbursements();
		int beforeSize = reimbsBefore.size();
		
		double amt = 45.34;
		BigDecimal amount = BigDecimal.valueOf(amt);
		
		Reimbursement reimb = new Reimbursement(amount, "test", 3, "FOOD");
		
		reimbServ.createReimbursement(reimb);
		
		List<Reimbursement> reimbsAfter = reimbServ.getAllReimbursements();
		int afterSize = reimbsAfter.size();
		
		
		assertEquals(beforeSize + 1, afterSize);
		
	
	}
	
	@Test
	public void updateReimbursementStatusTest() {
		
		Reimbursement before = reimbServ.getReimbursementById(15);
		String beforeStatus = before.getReimbStatus();
		int beforeStatusId = ReimbursementStatus.valueOf(beforeStatus).ordinal();
		System.out.println(beforeStatus);
		if(beforeStatusId == 2) {
			beforeStatusId = 0;
			reimbServ.changeReimbursementStatus(15, beforeStatusId, 1);
		}else {
			reimbServ.changeReimbursementStatus(15, beforeStatusId + 1, 1);
		}
			
		Reimbursement after = reimbServ.getReimbursementById(15);
		String afterStatus = after.getReimbStatus();
		System.out.println(afterStatus);
		assertNotEquals(beforeStatus, afterStatus);
		
	}
	
	@Test
	public void updateReimbursementFailsBecauseResolverDoesNotExist() {
		
		Reimbursement before = reimbServ.getReimbursementById(15);
		String beforeStatus = before.getReimbStatus();
		int beforeStatusId = ReimbursementStatus.valueOf(beforeStatus).ordinal();
		System.out.println(beforeStatus);
		
		try {
			if(beforeStatusId == 2) {
				beforeStatusId = 0;
				reimbServ.changeReimbursementStatus(15, beforeStatusId, 1);
			}else {
				reimbServ.changeReimbursementStatus(15, beforeStatusId + 1, 100);
			}
				
			Reimbursement after = reimbServ.getReimbursementById(15);
			String afterStatus = after.getReimbStatus();
			System.out.println(afterStatus);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail("Resolver does not exist");
		}
		
	}
	


}
