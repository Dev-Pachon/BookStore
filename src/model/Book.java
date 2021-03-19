package model;

public class Book {
	
	private int ISBNCod;
	private double price;
	private int numBooks;
	
	public Book(int iSBNCod, double price, int numBooks) {
		ISBNCod = iSBNCod;
		this.price = price;
		this.numBooks = numBooks;
	}
	
	public int getISBNCod() {
		return ISBNCod;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getNumBooks() {
		return numBooks;
	}
	
	public void setNumBooks(int numBooks) {
		this.numBooks = numBooks;
	}
}
