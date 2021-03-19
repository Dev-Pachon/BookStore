package model;

public class Book implements Comparable<Book>{
	
	private int ISBNCod;
	private double price;
	private int numBooks;
	private String shelfId;
	
	public Book(int iSBNCod, double price, int numBooks,String shelfId) {
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
	
	public void reduceNumBooks() {
		numBooks--;
	}

	public String getShelfId() {
		return shelfId;
	}

	@Override
	public int compareTo(Book book) {
		if(shelfId.compareTo(book.getShelfId())==0) {
			return ISBNCod <= book.getISBNCod() ? -1 : 1;
		}else
			return shelfId.compareTo(book.getShelfId());
	}
}
