package model;

public class Person {
	String fname;
	String lname;
	public Person() {
	}
	public Person( String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "Person [fname=" + fname + ", lname=" + lname + "]";
	}
	
	
}