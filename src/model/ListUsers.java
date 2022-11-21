package model;

import java.util.ArrayList;

public class ListUsers {
	private ArrayList<User> users;

	public ListUsers(ArrayList<User> users) {
		this.users = users;
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public boolean addUser(User user) {
		User aux = searchUser(user.getUserName());
		if (aux == null) {
			users.add(user);
			quicksort(users, 0, users.size()-1);
			return true;
		}
		return false;
	}
	
	public boolean isValid(String userName, String password) {
		User aux = searchUser(userName);
		return aux != null && aux.getPassword().equals(password) && aux.getState() == 1;
	}
	
	public void quicksort(ArrayList<User> A, int izq, int der) {
		User pivote = A.get(izq);
		int i = izq;
		int j = der;
		User aux;

		while (i < j) {
			while (A.get(i).getUserName().compareTo(pivote.getUserName()) <= 0 && i < j)
				i++;
			while (A.get(j).getUserName().compareTo(pivote.getUserName()) > 0)
				j--;
			if (i < j) {
				aux = A.get(i);
				A.set(i, A.get(j));
				A.set(j, aux);
			}
		}

		A.set(izq, A.get(j));
		A.set(j, pivote);

		if (izq < j - 1)
			quicksort(A, izq, j - 1);
		if (j + 1 < der)
			quicksort(A, j + 1, der);
	}
	
	public User searchUser(String userName) {
		int index = search(userName, 0, users.size());
		if (index != -1) {
			return users.get(index);
		}
		return null;
	}
	
	private int search(String userName, int start, int end) {
		if (users.size() == 0) {
			return -1;
		}else if (users.get(((end-start)/2)+start).getUserName().equals(userName)) {
			return ((end-start)/2)+start;
		}else if (start+1 == end) {
			return -1;
		}else if(users.get(((end-start)/2)+start).getUserName().compareTo(userName) > 0) {
			return search(userName, start, ((end-start)/2)+start);
		}else {
			return search(userName, ((end-start)/2)+start, end);
		}
	}

	public int getQuantityCashiers() {
		int quantity = 0;
		for (User user : users) {
			if (user.getTypeUser() == EnumTypeUser.CASHIER && user.getState() == 1) {
				quantity++;
			}
		}
		return quantity;
	}
}
