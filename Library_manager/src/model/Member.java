package model;

import java.sql.Date;

public class Member extends Person {
	
	Date joinDate;
	String activeStatus;
	

	public Member() {
	}

	public Member( String fName, String lName, Date joinDate, String activeStatus) {
		super(fName,lName);
		this.joinDate = joinDate;
		this.activeStatus = activeStatus;
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

	@Override
	public String toString() {
		return "Member ["+super.toString() +"joinDate=" + joinDate
				+ ", activeStatus=" + activeStatus + "]";
	}
	
	
	
}
