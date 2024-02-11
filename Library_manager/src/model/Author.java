package model;

import java.util.Scanner;

public class Author extends Person {
	
	public Author() {
	}

	public Author(String fName, String lName) {
		super(fName, lName);
	}

	Scanner scan = new Scanner(System.in);
	public void input()
	{
		System.out.print("Enter author's first name: ");
		this.fname = scan.nextLine(); 
		System.out.print("Enter author's last name: ");
		this.lname = scan.nextLine(); 
	}

	@Override
	public String toString() {
		return "Author [" + super.toString();
	}

}
