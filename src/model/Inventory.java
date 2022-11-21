package model;

import java.util.ArrayList;

public class Inventory {
	private String name;
	private ArrayList<Product> products;

	public Inventory(ArrayList<Product> products, String name) {
		this.products = products;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public boolean add(Product product) {
		if (!isExist(product.getId())) {
			products.add(product);
			quicksort(products, 0, products.size() - 1);
			return true;
		}
		return false;
	}

	public boolean remove(int id) {
		int index = search(id, 0, products.size());
		if (index != -1) {
			products.remove(index);
			return true;
		}
		return false;
	}

	public boolean isExist(int id) {
		return searchProduct(id) != null;
	}

	public boolean modifyPriceProduct(float price, int id) {
		Product product = searchProduct(id);
		if (product != null) {
			product.setPrice(price);
			return true;
		}
		return false;
	}

	public boolean modifyQuantityProduct(int quantity, int id) {
		Product product = searchProduct(id);
		if (product != null) {
			product.setQuantity(quantity);
			return true;
		}
		return false;
	}

	public void quicksort(ArrayList<Product> A, int izq, int der) {
		Product pivote = A.get(izq);
		int i = izq;
		int j = der;
		Product aux;

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
	
	public Product searchProduct(int id) {
		int index = search(id, 0, products.size());
		if (index != -1) {
			return products.get(index);
		}
		return null;
	}
	
	private int search(int id, int start, int end) {
		if (products.size() == 0) {
			return -1;
		}else if (products.get(((end-start)/2)+start).getId() == id) {
			return ((end-start)/2)+start;
		}else if (start+1 == end) {
			return -1;
		}else if(products.get(((end-start)/2)+start).getId() > id) {
			return search(id, start, ((end-start)/2)+start);
		}else {
			return search(id, ((end-start)/2)+start, end);
		}
	}

	public void removePurchaseToInventary(ArrayList<Invoice_Product> invoiceProducts) {
		for (Invoice_Product invoice_Product : invoiceProducts) {
			searchProduct(invoice_Product.getIdProduct()).substracQuantity(invoice_Product.getQuantity());
		}
	}

	public int getQuantityProducts() {
		int quantity = 0;
		for (Product product : products) {
			if (product.getState() == 1) {
				quantity++;
			}
		}
		return quantity;
	}

	public float[] getValuesToInvoice(ArrayList<Object[]> infoProductToInvoice, byte percentageOfProfit) {
		float[] valueProducts = getValueProducts(infoProductToInvoice);
		float subTotal = 0, ivaValue = 0, totalValue = 0;
		for (int i = 0; i < valueProducts.length; i++) {
			subTotal += (valueProducts[i] + (valueProducts[i]*percentageOfProfit))*(int)infoProductToInvoice.get(i)[1];
		}
		ivaValue = subTotal*Invoice.IVA_PERCENTAGE;
		totalValue = subTotal + ivaValue;
		return new float[] {subTotal, ivaValue, totalValue};
	}

	private float[] getValueProducts(ArrayList<Object[]> infoProductToInvoice) {
		float[] values = new float[infoProductToInvoice.size()];
		for (int i = 0; i < values.length; i++) {
			values[i] = searchProduct((int)infoProductToInvoice.get(i)[0]).getPrice();
		}
		return values;
	}

	public String getNameProduct(int idProduct) {
		return searchProduct(idProduct).getName();
	}

	public int getIdWarrantyToProduct(int idProduct) {
		return searchProduct(idProduct).getIdWarranty();
	}
	
}
