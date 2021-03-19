package model;

import dataStructures.Stack;

public class Client {
	private Stack<Book> basket;
	private int time;
	
	public Client() {
		basket = new Stack<Book>();
		time = 0;
	}
	
	public Stack<Book> getBasket() {
		return basket;
	}
	
	public void setBasket(Stack<Book> basket) {
		this.basket = basket;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
}
