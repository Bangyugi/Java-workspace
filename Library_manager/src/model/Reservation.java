package model;

import java.sql.Date;

public class Reservation {
	String book;
	String member;
	Date reservationDate;
	String reservationStatus;
	
	
	public Reservation() {
	}

	public Reservation( String book, String member, Date reservationDate, String reservationStatus) {
		super();
		this.book = book;
		this.member = member;
		this.reservationDate = reservationDate;
		this.reservationStatus = reservationStatus;
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

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	@Override
	public String toString() {
		return "Reservation [book=" + book + ", member=" + member + ", reservationDate="
				+ reservationDate + ", reservationStatus=" + reservationStatus + "]";
	}
	
	
}