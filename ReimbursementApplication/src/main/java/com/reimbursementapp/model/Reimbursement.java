package com.reimbursementapp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Reimbursement {
	
	int reimbId;
	private BigDecimal reimbAmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescription;
	private byte[] reimbReceipt;
	private int reimbAuthor;
	private int reimbResolver;
	//private int reimbStatusId;
	//private int reimbTypeId;
	private String reimbStatus;
	private String reimbType;
	
	
	
	public Reimbursement() {
		
	}
	

	public Reimbursement(int reimbId, BigDecimal reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, byte[] reimbReceipt, int reimbAuthor, int reimbResolver,
			String reimbStatus, String reimbType) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatus = reimbStatus.toUpperCase();
		this.reimbType = reimbType.toUpperCase();
	}
	
	public Reimbursement(BigDecimal reimbAmount, String reimbDescription, int reimbAuthor, String reimbType) {
		super();
		
		this.reimbAmount = reimbAmount;
		this.reimbDescription = reimbDescription;
		//this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		//this.reimbStatus = reimbStatus.toUpperCase();
		this.reimbType = reimbType.toUpperCase();
	}


	public int getReimb_id() {
		return reimbId;
	}



	public void setReimb_id(int reimb_id) {
		this.reimbId = reimb_id;
	}



	public BigDecimal getReimb_amount() {
		return reimbAmount;
	}



	public void setReimb_amount(BigDecimal reimb_amount) {
		this.reimbAmount = reimb_amount;
	}



	public Timestamp getReimb_submitted() {
		return reimbSubmitted;
	}



	public void setReimb_submitted(Timestamp reimb_submitted) {
		this.reimbSubmitted = reimb_submitted;
	}



	public Timestamp getReimb_resolved() {
		return reimbResolved;
	}



	public void setReimb_resolved(Timestamp reimb_resolved) {
		this.reimbResolved = reimb_resolved;
	}



	public String getReimb_description() {
		return reimbDescription;
	}



	public void setReimb_description(String reimb_description) {
		this.reimbDescription = reimb_description;
	}



	public byte[] getReimb_receipt() {
		return reimbReceipt;
	}



	public void setReimb_receipt(byte[] reimb_receipt) {
		this.reimbReceipt = reimb_receipt;
	}



	public int getReimb_author() {
		return reimbAuthor;
	}



	public void setReimb_author(int reimb_author) {
		this.reimbAuthor = reimb_author;
	}



	public int getReimb_resolver() {
		return reimbResolver;
	}



	public void setReimb_resolver(int reimb_resolver) {
		this.reimbResolver = reimb_resolver;
	}


	public String getReimbStatus() {
		return reimbStatus;
	}


	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}


	public String getReimbType() {
		return reimbType;
	}


	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbReceipt=" + reimbReceipt + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver
				+ ", reimbStatus=" + reimbStatus + ", reimbType=" + reimbType + "]";
	}


	
	
	
}
