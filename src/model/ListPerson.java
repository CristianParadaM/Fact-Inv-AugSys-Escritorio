package model;

import java.util.ArrayList;

public class ListPerson {
	private ArrayList<Person> persons;

	public ListPerson(ArrayList<Person> persons) {
		this.persons = persons;
	}
	
	public ArrayList<Person> getPersons() {
		return persons;
	}
	
	public void addPerson(Person person) {
		Person aux = searchPerson(person.getId());
		if (aux != null) {
			if (aux.isEqual(person)) {
				return;
			}
		}
		persons.add(person);
		quicksort(persons, 0, persons.size()-1);
	}
	
	public void quicksort(ArrayList<Person> A, int izq, int der) {
		Person pivote = A.get(izq);
		int i = izq;
		int j = der;
		Person aux;

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
	
	public Person searchPerson(int id) {
		int index = search(id, 0, persons.size());
		if (index != -1) {
			return persons.get(index);
		}
		return null;
	}
	
	private int search(int id, int start, int end) {
		if (persons.size() == 0) {
			return -1;
		}else if (persons.get(((end-start)/2)+start).getId() == id) {
			return ((end-start)/2)+start;
		}else if (start+1 == end) {
			return -1;
		}else if(persons.get(((end-start)/2)+start).getId() > id) {
			return search(id, start, ((end-start)/2)+start);
		}else {
			return search(id, ((end-start)/2)+start, end);
		}
	}
	
	/**
	 * Metodo para obtener el nombre de una persona
	 * @param idPerson id de la persona
	 * @return nombre del cliente
	 */
	public String getNamePerson(int idPerson) {
		Person person = searchPerson(idPerson);
		return person.getName()+" "+person.getLastName();
	}

	public int getIdDocumentType(int idClient) {
		return searchPerson(idClient).getIdDocumentType();
	}
}
