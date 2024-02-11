package model;

import java.sql.Date;

public class FinePayment {
	String member;
	Date paymentDate;
	int paymenrAmount;

	public FinePayment() {
		super();
	}

	public FinePayment(String member, Date paymentDate, int paymenrAmount) {
		super();
		this.member = member;
		this.paymentDate = paymentDate;
		this.paymenrAmount = paymenrAmount;
	}


	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
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

	@Override
	public String toString() {
		return "FinePayment [member=" + member + ", paymentDate=" + paymentDate + ", paymenrAmount="
				+ paymenrAmount + "]";
	}

	
}
