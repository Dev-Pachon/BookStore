package model;

import java.util.Arrays;

import dataStructures.HashTable;

public class Bookstore {

	//	private HashTable<String, Shelv> shelves;

	private HashTable<Integer, Book> booksDataBase;

	private HashTable<String, Client> clientsDataBase;

	public Bookstore() {
		//		shelves = new HashTable<>();
		booksDataBase = new HashTable<>();
	}

	//	public void addShelf(String id) {
	//		shelves.add(id, new Shelv(id, 0));
	//	}

	public void addBook(String idShelf, int ISBNCod, double price, int num) {

		Book newBook = new Book(ISBNCod, price, num, idShelf);

		booksDataBase.add(ISBNCod, newBook);
	}

	public void addClient(String id) {

		Client newClient = new Client();		

		//add the client to the dataBase
		clientsDataBase.add(id, newClient);

	}

	public void pickUpBooks(Client client,int[] ISBNCods, SortingType type) {
		Book[] books = new Book[ISBNCods.length];

		//put the available books into an array
		int pos = -1;
		for(int i = 0;i<ISBNCods.length;i++) {
			Book temp =booksDataBase.get(ISBNCods[i]);
			if(temp!=null) {
				books[++pos] = temp;
				temp.reduceNumBooks();
				if(temp.getNumBooks()==0) {
					booksDataBase.remove(temp.getISBNCod());
				}
			}
		}

		// If the quantity of books isn't equal to the length of the ISBN Cods -> books will be of the length just of the books available. 
		if(pos < ISBNCods.length-1) {
			books = Arrays.copyOf(books, pos); 
		}

		sortBooks(type, books);
		//add the books to the stack
		for(Book book : books)
			client.addBook(book);
	}

	public Book[] sortBooks(SortingType type, Book[] clientBooks) {

		switch (type) {
		case COUNTINGSORT:

			break;
		case QUICKSORT:
			quickSort(clientBooks, 0, clientBooks.length-1);
			break;
		case BUBBLESORT:
			bubbleSort(clientBooks);
			break;
		}

		return clientBooks;
	}

	private<T extends Comparable<T>> int partition(T arr[], int low, int high) {
		T pivot = arr[high];  
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) {
			if (arr[j].compareTo(pivot)<0) { 
				i++; 

				T temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 

		T temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 

		return i+1;
	}

	private<T extends Comparable<T>> void quickSort(T arr[], int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			quickSort(arr, low, pi-1);
			quickSort(arr, pi+1, high); 
		} 
	}

	private<T extends Comparable<T>> void bubbleSort(T arr[]) 
	{ 
		int n = arr.length; 
		for (int i = 0; i < n-1; i++) 
			for (int j = 0; j < n-i-1; j++) 
				if (arr[j].compareTo(arr[j+1])>0) 
				{ 
					// swap arr[j+1] and arr[j] 
					T temp = arr[j]; 
					arr[j] = arr[j+1]; 
					arr[j+1] = temp; 
				} 
	}

}
