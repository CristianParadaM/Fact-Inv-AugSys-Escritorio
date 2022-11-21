package view.cashier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import view.JFrameMain;

public class JPanelProducts extends JPanel {

	private static final int WIDTH = (1600 * JFrameMain.WIDTH_SIZE / 1920);
	private static final int HEIGTH = 660 * JFrameMain.HEIGTH_SIZE / 1080;
	private static final long serialVersionUID = 1L;
	private JPanel jPanelContent;
	private JScrollPane jScrollPane;
	private ArrayList<Object[]> listProducts;
	private ArrayList<Object[]> productList;
	private int widhtCard;
	private int heightCard;
	private int xCard;
	private int yCard;
	private int rows;

	/**
	 * Constructor de JPanelProducts
	 * @param listProducts lista de productos
	 */
	public JPanelProducts(ArrayList<Object[]> listProducts) {
		super(new GridLayout());
		this.productList = new ArrayList<Object[]>();
		this.rows = 1;
		this.jPanelContent = new JPanel();
		this.widhtCard = (WIDTH / 5) - (40* JFrameMain.WIDTH_SIZE/1920);
		this.heightCard = (HEIGTH / 3 * 2);
		this.listProducts = listProducts;
		this.jScrollPane = new JScrollPane();
		init();
	}

	/**
	 * Metodo que inicializa este panel
	 */
	private void init() {
		this.jPanelContent.setOpaque(false);
		this.jPanelContent.setLayout(null);
		this.jPanelContent.setPreferredSize(new Dimension(WIDTH, HEIGTH));
		this.jPanelContent.setSize(new Dimension(WIDTH, HEIGTH));
		this.setOpaque(false);
		putProducts();
		this.jScrollPane.setViewportView(jPanelContent);
		this.jScrollPane.setOpaque(false);
		this.jScrollPane.getViewport().setOpaque(false);
		this.jScrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10 * JFrameMain.WIDTH_SIZE / 1920, 0));
		this.jScrollPane.getHorizontalScrollBar().setOpaque(false);
		this.jScrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(48, 116, 180);
			}
		});
		this.add(jScrollPane);
	}

	/**
	 * Metodo que actualiza la lista de productos
	 * @param products productos
	 */
	public void setProducts(ArrayList<Object[]> products) {
		this.listProducts = products;
		putProducts();
	}

	/**
	 * Metodo que pone los productos en el inventario
	 */
	private void putProducts() {
		setvisibleAll(false);
		this.xCard = this.yCard = 0;
		for (int i = 0; i < listProducts.size(); i++) {
			addProduct(i);
			if (xCard + widhtCard >= WIDTH) {
				xCard = 0;
				yCard = yCard + heightCard + (5 * JFrameMain.HEIGTH_SIZE / 1080);
				rows++;
				jPanelContent.setPreferredSize(
						new Dimension(WIDTH, rows * heightCard + ((5 * JFrameMain.HEIGTH_SIZE / 1080) * rows)));
			} else {
				xCard += widhtCard + (10 * JFrameMain.WIDTH_SIZE/1920);
			}
		}
	}

	/**
	 * Metodo que crea las cartas de presentacion y las agrega a la lista del inventario visual 
	 * @param index posicion
	 */
	private void addProduct(int index) {
		JPanelCard jPanelCard = new JPanelCard(listProducts.get(index), widhtCard, heightCard);
		this.jPanelContent.add(jPanelCard).setBounds(xCard, yCard, widhtCard, heightCard);
		this.productList.add(new Object[] { jPanelCard, true });
	}

	/**
	 * Metodo que quita los filtros 
	 */
	public void putOffFilters() {
		for (Object[] product : productList) {
			product[1] = true;
		}
		reorganize();
	}

	/**
	 * Metodo que filtra la informacion del inventario
	 * @param text texto a filtrar
	 * @param filters filtros aplicados
	 */
	@SuppressWarnings("unchecked")
	public void filtre(String text, int[] filters) {
		ArrayList<Object[]> componentsFiltred = new ArrayList<Object[]>();
		text = text.replace("	", "").replace("", "").toLowerCase();
		if (text.isBlank()) {
			componentsFiltred = (ArrayList<Object[]>) productList.clone();
			componentsFiltred = applyFilters(componentsFiltred, filters);
			organize(componentsFiltred);
			return;
		} else {
			for (int i = 0; i < this.jPanelContent.getComponents().length; i++) {
				if ((this.jPanelContent.getComponent(i).getName().toLowerCase()).contains(text)) {
					componentsFiltred.add(new Object[] { (JPanelCard) this.jPanelContent.getComponent(i), true });
				} else {
					this.jPanelContent.getComponent(i).setVisible(false);
				}
			}
		}
		if (!componentsFiltred.isEmpty()) {
			componentsFiltred = applyFilters(componentsFiltred, filters);
			organize(componentsFiltred);
			this.jScrollPane.getVerticalScrollBar().setValue(0);
		}

	}

	/**
	 * Metodo que aplica los filtros de busqueda
	 * @param productList lista a filtrar
	 * @param filters filtros a aplicar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyFilters(ArrayList<Object[]> productList, int[] filters) {
		switch (filters[0]) {
		case 1 -> productList = applyComponentFilter(productList);
		case 2 -> productList = applyPhoneFilter(productList);
		case 3 -> productList = applyLaptopFilter(productList);
		case 4 -> productList = applyPCFilter(productList);
		case 5 -> productList = applyAccesoryFilter(productList);
		}
		switch (filters[1]) {
		case 1 -> productList = applyPrice500kFilter(productList);
		case 2 -> productList = applyPrice1MFilter(productList);
		case 3 -> productList = applyPrice5MFilter(productList);
		case 4 -> productList = applyPrice5MMFilter(productList);
		}
		return productList;
	}

	/**
	 * Metodo que pone los filtros de producto de mas de 5M
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyPrice5MMFilter(ArrayList<Object[]> productList2) {
		for (Object[] product : productList2) {
			if (((JPanelCard) product[0]).getPrice() >= 5000000) {
				product[1] = true;
			} else {
				product[1] = false;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que pone los filtros de producto de 1M a 5M
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyPrice5MFilter(ArrayList<Object[]> productList2) {
		for (Object[] product : productList2) {
			if (((JPanelCard) product[0]).getPrice() >= 1000000 && ((JPanelCard) product[0]).getPrice() <= 4999999) {
				product[1] = true;
			} else {
				product[1] = false;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que pone los filtros de producto de 500k a 1M
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyPrice1MFilter(ArrayList<Object[]> productList2) {
		for (Object[] product : productList2) {
			if (((JPanelCard) product[0]).getPrice() >= 500000 && ((JPanelCard) product[0]).getPrice() <= 999999) {
				product[1] = true;
			} else {
				product[1] = false;
			}
		}
		return productList2;
	}

	
	/**
	 * Metodo que pone los filtros de producto de 0k a 500k
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyPrice500kFilter(ArrayList<Object[]> productList2) {
		for (Object[] product : productList2) {
			if (((JPanelCard) product[0]).getPrice() <= 499000) {
				product[1] = true;
			} else {
				product[1] = false;
			}
		}
		return productList2;
	}

	
	/**
	 * Metodo que pone los filtros de producto de accesorios
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyAccesoryFilter(ArrayList<Object[]> productList2) {
		for (int i = 0; i < productList2.size(); i++) {
			if (((JPanelCard) productList2.get(i)[0]).getType().equals("Accesorio")) {
				productList2.get(i)[1] = true;
			} else {
				productList2.get(i)[1] = false;
				productList2.remove(i);
				i--;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que pone los filtros de producto de PC
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyPCFilter(ArrayList<Object[]> productList2) {
		for (int i = 0; i < productList2.size(); i++) {
			if (((JPanelCard) productList2.get(i)[0]).getType().equals("PC")) {
				productList2.get(i)[1] = true;
			} else {
				productList2.get(i)[1] = false;
				productList2.remove(i);
				i--;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que pone los filtros de producto de Laptop
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyLaptopFilter(ArrayList<Object[]> productList2) {
		for (int i = 0; i < productList2.size(); i++) {
			if (((JPanelCard) productList2.get(i)[0]).getType().equals("Laptop")) {
				productList2.get(i)[1] = true;
			} else {
				productList2.get(i)[1] = false;
				productList2.remove(i);
				i--;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que pone los filtros de producto de Celulares
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyPhoneFilter(ArrayList<Object[]> productList2) {
		for (int i = 0; i < productList2.size(); i++) {
			if (((JPanelCard) productList2.get(i)[0]).getType().equals("Celulares")) {
				productList2.get(i)[1] = true;
			} else {
				productList2.get(i)[1] = false;
				productList2.remove(i);
				i--;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que pone los filtros de producto de Componente
	 * @param productList2 lista a filtrar
	 * @return lista filtrada
	 */
	private ArrayList<Object[]> applyComponentFilter(ArrayList<Object[]> productList2) {
		for (int i = 0; i < productList2.size(); i++) {
			if (((JPanelCard) productList2.get(i)[0]).getType().equals("Componente")) {
				productList2.get(i)[1] = true;
			} else {
				productList2.get(i)[1] = false;
				productList2.remove(i);
				i--;
			}
		}
		return productList2;
	}

	/**
	 * Metodo que reorganiza el inventario
	 */
	private void reorganize() {
		setvisibleAll(false);
		xCard = yCard = 0;
		int rows = 1;
		jPanelContent.setPreferredSize(new Dimension(WIDTH, rows * heightCard));
		for (Object card[] : productList) {
			if ((boolean) card[1]) {
				this.jPanelContent.add((JPanelCard) card[0]).setBounds(xCard, yCard, widhtCard, heightCard);
				((JPanelCard) card[0]).setVisible(true);
				if (xCard + widhtCard >= WIDTH) {
					xCard = 0;
					yCard = yCard + heightCard + (5* JFrameMain.HEIGTH_SIZE/1080);
					rows++;
					jPanelContent.setPreferredSize(new Dimension(WIDTH, rows * heightCard + ((5* JFrameMain.HEIGTH_SIZE/1080) * rows)));
				} else {
					xCard += widhtCard + (10 * JFrameMain.WIDTH_SIZE/1920);
				}
			}
		}
	}

	/**
	 * Metodo que organiza el inventario
	 * @param productsFiltred productos filtrados
	 */
	private void organize(ArrayList<Object[]> productsFiltred) {
		setvisibleAll(false);
		xCard = yCard = 0;
		int rows = 1;
		jPanelContent.setPreferredSize(new Dimension(WIDTH, rows * heightCard));
		for (Object[] card : productsFiltred) {
			if ((boolean) card[1]) {
				this.jPanelContent.add((JPanelCard) card[0]).setBounds(xCard, yCard, widhtCard, heightCard);
				((JPanelCard) card[0]).setVisible(true);
				if (xCard + widhtCard >= WIDTH) {
					xCard = 0;
					yCard = yCard + heightCard + (5* JFrameMain.HEIGTH_SIZE/1080);
					rows++;
					jPanelContent.setPreferredSize(new Dimension(WIDTH, rows * heightCard + ((5* JFrameMain.HEIGTH_SIZE/1080) * rows)));
				} else {
					xCard += widhtCard + (10 * JFrameMain.WIDTH_SIZE/1920);
				}
			}
		}

	}

	/**
	 * Metodo que pone o no visibles todos los componentes de jpanelContent
	 * @param b true o false
	 */
	private void setvisibleAll(boolean b) {
		for (int i = 0; i < this.jPanelContent.getComponents().length; i++) {
			this.jPanelContent.getComponent(i).setVisible(b);
		}
	}

	/**
	 * Metodo que pone en cero la cantidad de todos las cartas de presentacion del inventario
	 */
	public void setCeroQuanty() {
		for (int i = 0; i < this.jPanelContent.getComponents().length; i++) {
			((JPanelCard) this.jPanelContent.getComponent(i)).setCeroQuanty();
		}
	}

	/**
	 * Metodo que obtiene la informacion de algun producto
	 * @param code codigo del producto
	 * @return informacion del producto
	 */
	public Object[] getProductInfo(int code) {
		for (int i = 0; i < this.jPanelContent.getComponents().length; i++) {
			if (((JPanelCard) this.jPanelContent.getComponent(i)).getCode() == code) {
				return ((JPanelCard) this.jPanelContent.getComponent(i)).getProductInfoToBill();
			}
		}
		return null;
	}

	/**
	 * Metodo que obtiene la cantidad maxima de un producto
	 * @param code codigo del producto
	 * @return cantidad maxima del producto
	 */
	public int getMaxQuantyOf(int code) {
		for (Object[] product : listProducts) {
			if ((int) product[4] == code) {
				return (int) product[1];
			}
		}
		return -1;
	}

}
