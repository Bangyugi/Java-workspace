package model;

import java.sql.Date;

public class Loan {
	String book;
	String member;
	Date loan_Date;
	Date return_Date;

	public Loan() {
	}

	public Loan(String book, String member, Date loan_Date, Date return_Date) {
		super();
		this.book = book;
		this.member = member;
		this.loan_Date = loan_Date;
		this.return_Date = return_Date;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Date getLoan_Date() {
		return loan_Date;
	}

	public void setLoan_Date(Date loan_Date) {
		this.loan_Date = loan_Date;
	}

	public Date getReturn_Date() {
		return return_Date;
	}

	public void setReturn_Date(Date return_Date) {
		this.return_Date = return_Date;
	}

	@Override
	public String toString() {
		return "Loan [book=" + book + ", member=" + member + ", loan_Date=" + loan_Date
				+ ", return_Date=" + return_Date + "]";
	}
	

}
