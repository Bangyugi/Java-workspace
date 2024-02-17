package model;

import java.util.Scanner;

public class Fine {
	int member_id;
	int loan_id;
	String fine_date;
	int fine_amount;
	String note;

	public Fine() {
	}

	public Fine(int member_id, int loan_id, String fine_date, int fine_amount, String note) {
		this.member_id = member_id;
		this.loan_id = loan_id;
		this.fine_date = fine_date;
		this.fine_amount = fine_amount;
		this.note = note;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public String getFine_date() {
		return fine_date;
	}

	public void setFine_date(String fine_date) {
		this.fine_date = fine_date;
	}

	public int getFine_amount() {
		return fine_amount;
	}

	public void setFine_amount(int fine_amount) {
		this.fine_amount = fine_amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	Scanner scan = new Scanner(System.in);
	public void input()
	{
		System.out.print("Enter member's id: ");
		this.member_id = scan.nextInt();
		System.out.print("Enter loan's id: ");
		this.loan_id = scan.nextInt();
		scan.nextLine();
		System.out.print("Enter fine date: ");
		this.fine_date = scan.nextLine();
		System.out.print("Enter fine amount: ");
		this.fine_amount = scan.nextInt();
	}
	
	

}
