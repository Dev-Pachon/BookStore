package model;

import dataStructures.HashTable;

public class Bookstore {

	private HashTable<String, Shelv> shelves;

	public Bookstore() {
		shelves = new HashTable<>();
	}

	public void addShelf(String id) {
		shelves.add(id, new Shelv(id, 0));
	}

	public void addBook(String idShelf, String ISBNCod, double price, int num) {

		Shelv shelf = shelves.get(idShelf);

		if(shelf !=null) {
//			shelf.addBook(ISBNCod, price, num);
		}

	}

	public Book[] sortBooks(SortingType type) {
		
		return null;
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
	
	

}
