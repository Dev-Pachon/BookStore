package model;

import dataStructures.Stack;

public class Client {
	private Stack<Book> basket;
	private int time;
	private String id;
	
	public Client(String id, int time) {
		basket = new Stack<Book>();
		time = 0;
		this.id = id;
	}
	
	public Stack<Book> getBasket() {
		return basket;
	}
	
	public void addBook(Book book) {
		basket.push(book);
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}
}
