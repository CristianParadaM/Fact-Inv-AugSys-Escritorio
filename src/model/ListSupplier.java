package model;

import java.util.ArrayList;

public class ListSupplier {
	private ArrayList<Supplier> suppliers;

	public ListSupplier(ArrayList<Supplier> suppliers) {
		this.suppliers = suppliers;
	}
	
	public void addSupplier(Supplier supplier) {
		Supplier aux = searchSupplier(supplier.getId());
		if (aux != null) {
			aux = supplier;
		}else {
			suppliers.add(supplier);
		}
	}
	
	public void quicksort(ArrayList<Supplier> A, int izq, int der) {
		Supplier pivote = A.get(izq);
		int i = izq;
		int j = der;
		Supplier aux;

		while (i < j) {
			while (A.get(i).getId() <= pivote.getId() && i < j)
				i++;
			while (A.get(j).getId() > pivote.getId())
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
	
	/**
	 * Metodo para buscar un proovedor
	 * @param id identificador del proovedor
	 * @return proovedor si se encontro o null si no
	 */
	public Supplier searchSupplier(int id) {
		int index = search(id, 0, suppliers.size());
		if (index != -1) {
			return suppliers.get(index);
		}
		return null;
	}
	
	private int search(int id, int start, int end) {
		if (suppliers.size() == 0) {
			return -1;
		}else if (suppliers.get(((end-start)/2)+start).getId() == id) {
			return ((end-start)/2)+start;
		}else if (start+1 == end) {
			return -1;
		}else if(suppliers.get(((end-start)/2)+start).getId() > id) {
			return search(id, start, ((end-start)/2)+start);
		}else {
			return search(id, ((end-start)/2)+start, end);
		}
	}
}
