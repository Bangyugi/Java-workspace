package model;

import java.util.Scanner;

public class Loan {
	int book_id;
	int member_id;
	String loan_date;
	String return_date;

	public Loan() {
	}

	public Loan(int book_id, int member_id, String loan_date, String return_date) {
		this.book_id = book_id;
		this.member_id = member_id;
		this.loan_date = loan_date;
		this.return_date = return_date;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getLoan_date() {
		return loan_date;
	}

	public void setLoan_date(String loan_date) {
		this.loan_date = loan_date;
	}

	public String getReturn_date() {
		return return_date;
	}

	public void setReturn_date(String return_date) {
		this.return_date = return_date;
	}

	Scanner scan = new Scanner(System.in);
	public void input()
	{
		System.out.print("Enter book's id: ");
		this.book_id = scan.nextInt();
		System.out.print("Enter member's id: ");
		this.member_id = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter loan date (yyyy-mm-dd): ");
		this.loan_date = scan.nextLine();
	}
	

}
