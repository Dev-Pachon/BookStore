package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;

import dataStructures.HashTable;
import dataStructures.Queue;

public class Bookstore {

	private HashTable<Integer, Book> booksDataBase;

	private HashTable<String, Client> clientsDataBase;
	
	private Queue<Client> payQueue;

	public Bookstore() {
		booksDataBase = new HashTable<>();
		clientsDataBase = new HashTable<>();
		payQueue = new Queue<>();
	}

	public void addBook(String idShelf, int ISBNCod, double price, int num) {

		Book newBook = new Book(ISBNCod, price, num, idShelf);

		booksDataBase.add(ISBNCod, newBook);
	}

	private void Simulate(File file, SortingType type) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		int numCajeros = Integer.parseInt(br.readLine());
		//declaration of the cashiers
		
		
		
		
		int numShelves = Integer.parseInt(br.readLine());

		for(int i = 0 ; i < numShelves ; i++) {

			String[] entry = br.readLine().split(" "); 

			String idShelf = entry[0];
			int numBooks = Integer.parseInt(entry[1]);

			for(int j = 0 ; j < numBooks ;j++) {
				entry = br.readLine().split(" ");

				int ISBNCod = Integer.parseInt(entry[0]);
				double price = Double.parseDouble(entry[1]);
				int quantity = Integer.parseInt(entry[2]);

				Book book = new Book(ISBNCod, price, quantity, idShelf);

				booksDataBase.add(ISBNCod, book);
			}
		}

		int numClient = Integer.parseInt(br.readLine());

		for (int i = 0; i < numClient; i++) {
			
			String entry[] = br.readLine().split(" ");
			
			String id = entry[0];
			
			Client newClient = new Client(id,i+1);		

			//add the client to the dataBase
			clientsDataBase.add(id, newClient);
			
			int[] ISBNCod = new int[entry.length-1];
			
			for(int j = 0 ; j < ISBNCod.length ; j++)
				ISBNCod[j] = Integer.parseInt(entry[j+1]);
			
			pickUpBooks(newClient, ISBNCod, type);
		}
		
		
		
		
		
		br.close();
	}

	private void pickUpBooks(Client client,int[] ISBNCods, SortingType type) {
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
		for(Book book : books){
			client.getBasket().push(book);
			client.setTime(client.getTime()+1);
		}

		payQueue.
	}

	public void section4(){



	}

	public void sortBooks(SortingType type, Book[] clientBooks) {

		switch (type) {
		case MERGESORT:
			mergeSort(clientBooks, 0, clientBooks.length-1);
			break;
		case QUICKSORT:
			quickSort(clientBooks, 0, clientBooks.length-1);
			break;
		case BUBBLESORT:
			bubbleSort(clientBooks);
			break;
		}
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
					T temp = arr[j]; 
					arr[j] = arr[j+1]; 
					arr[j+1] = temp; 
				} 
	}


	@SuppressWarnings("unchecked")
	private<T extends Comparable<T>> void merge(T arr[],int l, int m, int r) {
		int n1 = m-l+1;
		int n2 = r-m;

		T L[] = (T[])new Object[n1];
		T R[] = (T[])new Object[n2];

		for(int i = 0; i< n1;++i)
			L[i] = arr[l + i];
		for (int i = 0; i < n2; ++i) 
			R[i] = arr[m+1+i];

		int i = 0;
		int j = 0;
		int k = l;

		while(i < n1 && j < n2) {
			if(L[i].compareTo(R[j])<=0) {
				arr[k] = L[i];
				i++;
			}else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while(i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		while(j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	private<T extends Comparable<T>> void mergeSort(T arr[], int l, int r) {
		if(l < r) {
			int m = (l + r) / 2;

			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			merge(arr, l, m, r);
		}
	}
}
