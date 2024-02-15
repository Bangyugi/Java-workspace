package model;

import java.sql.Date;
import java.util.Scanner;

public class Member extends Person {

	Date joinDate;
	String phone;
	String activeStatus;

	public Member() {
	}

	public Member(Date joinDate, String phone, String activeStatus) {
		this.joinDate = joinDate;
		this.phone = phone;
		this.activeStatus = activeStatus;
	}

	public Member(String fname, String lname, Date joinDate, String phone, String activeStatus) {
		super(fname, lname);
		this.joinDate = joinDate;
		this.phone = phone;
		this.activeStatus = activeStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	Scanner scan = new Scanner(System.in);

	public void input() {
		System.out.print("Enter member's first name: ");
		this.fname = scan.nextLine();
		System.out.print("Enter member's last name: ");
		this.lname = scan.nextLine();
		System.out.print("Enter member's phone: ");
		this.phone = scan.nextLine();
	}

	@Override
	public String toString() {
		return "Member [" + super.toString() + "joinDate=" + joinDate
				+ ", activeStatus=" + activeStatus + "]";
	}

}
