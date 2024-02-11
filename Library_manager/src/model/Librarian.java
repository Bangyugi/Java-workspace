package model;

public class Librarian extends Person {
	String address;
	String email;
	String phone;
	public Librarian() {
	}
	
	public Librarian( String fname, String lname, String address, String email, String phone) {
		super(fname, lname);
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Librarian [" +super.toString() + "address=" + address + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
}
