package model;

import java.sql.Date;

public class FinePayment {
	int id;
	int memberID;
	String note;
	Date paymentDate;
	int paymenrAmount;

	public FinePayment() {
	}

	public FinePayment(int id, int memberID, String note, Date paymentDate, int paymenrAmount) {
		this.id = id;
		this.memberID = memberID;
		this.note = note;
		this.paymentDate = paymentDate;
		this.paymenrAmount = paymenrAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public int getPaymenrAmount() {
		return paymenrAmount;
	}

	public void setPaymenrAmount(int paymenrAmount) {
		this.paymenrAmount = paymenrAmount;
	}

	

	
}


