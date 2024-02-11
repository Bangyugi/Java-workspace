package model;

import java.util.Scanner;

public class Book {
	String title;
	String category;
	String publicationDate;
	int copiesOwned;
	
	
	public Book() {
	}


	public Book( String title, String category, String publicationDate, int copiesOwned) {
		super();

		this.title = title;
		this.category = category;
		this.publicationDate = publicationDate;
		this.copiesOwned = copiesOwned;
	}



	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getPublicationDate() {
		return publicationDate;
	}


	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}


	public int getCopiesOwned() {
		return copiesOwned;
	}


	public void setCopiesOwned(int copiesOwned) {
		this.copiesOwned = copiesOwned;
	}

	Scanner scan = new Scanner(System.in);
	public void intput()
	{
		System.out.print("Enter title: ");
		this.title = scan.nextLine();
		System.out.print("Enter category: ");
		this.category = scan.nextLine();
		System.out.print("Enter publication date: ");
		this.publicationDate = scan.nextLine();
		System.out.print("Enter copies owned: ");
		this.copiesOwned = scan.nextInt();
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", category=" + category + ", publicationDate=" + publicationDate
				+ ", copiesOwned=" + copiesOwned + "]";
	}
	
		
	

	
	
	
}
