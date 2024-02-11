package model;

import java.sql.Date;

public class Fine {
	String member;
	int loan_id;
	Date fineDate;
	int fineAmount;

	public Fine() {
	}

	public Fine( String member, int loan_id, Date fineDate, int fineAmount) {
		super();
		this.member = member;
		this.loan_id = loan_id;
		this.fineDate = fineDate;
		this.fineAmount = fineAmount;
	}


	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public Date getFineDate() {
		return fineDate;
	}

	public void setFineDate(Date fineDate) {
		this.fineDate = fineDate;
	}

	public int getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(int fineAmount) {
		this.fineAmount = fineAmount;
	}

	@Override
	public String toString() {
		return "Fine [member=" + member + ", loan_id=" + loan_id + ", fineDate=" + fineDate
				+ ", fineAmount=" + fineAmount + "]";
	}
	
	
	

}
