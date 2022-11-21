package view.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import view.JFrameMain;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JTableBill extends JScrollPane {

	private static final String PRECIO_TOTAL_$ = "Precio Total $.";
	private static final String PRECIO_UNITARIO_$ = "Precio Unitario $.";
	private static final String DESCRIPCION = "Descripci�n";
	private static final String CANTIDAD = "Cantidad";
	private static final String CODIGO = "C�digo";
	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private ArrayList<Object[]> products;
	private String[] columnNames;
	private int totalItems;

	/**
	 * Constructor de JTableBill
	 */
	public JTableBill() {
		super();
		this.products = new ArrayList<Object[]>();
		this.columnNames = new String[] { CODIGO, CANTIDAD, DESCRIPCION, PRECIO_UNITARIO_$, PRECIO_TOTAL_$ };
		this.totalItems = products.size();
		init();
	}
	
	/**
	 * Metodo que inicia este tabla
	 */
	private void init() {
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		this.getVerticalScrollBar().setOpaque(false);
		this.getHorizontalScrollBar().setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());

		DefaultTableCellRenderer cellRenderMoney = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void setValue(Object value) {
				DecimalFormat formato = new DecimalFormat("#,###.00");
				setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
				setForeground(Color.BLACK);
				setOpaque(false);
				super.setValue(formato.format((double) value));
			}
		};
		cellRenderMoney.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer cellRendererText = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void setValue(Object value) {
				setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
				setForeground(Color.BLACK);
				setOpaque(false);
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				super.setValue(value);
			}
		};
		cellRendererText.setHorizontalAlignment(JLabel.CENTER);

		TableModel dataModel = new AbstractTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public int getColumnCount() {
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				return products.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				return products.get(row)[col];
			}

			@Override
			public String getColumnName(int column) {
				return columnNames[column];
			}

			@Override
			public Class<? extends Object> getColumnClass(int c) {
				return getValueAt(0, c).getClass();
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				products.get(row)[column] = aValue;
			}
		};

		jTable = new JTable(dataModel);
		jTable.setOpaque(false);
		jTable.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
		jTable.getTableHeader().setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25* JFrameMain.WIDTH_SIZE/1920));
		jTable.getTableHeader().setBorder(new LineBorder(new Color(48, 116, 180), 3));
		jTable.getTableHeader().setForeground(new Color(48, 116, 180));

		TableColumn codeColumn = jTable.getColumn(CODIGO);
		TableColumn quantyColumn = jTable.getColumn(CANTIDAD);
		TableColumn descriptionColumn = jTable.getColumn(DESCRIPCION);
		TableColumn priceUColumn = jTable.getColumn(PRECIO_UNITARIO_$);
		TableColumn priceTColumn = jTable.getColumn(PRECIO_TOTAL_$);
		codeColumn.setCellRenderer(cellRendererText);
		codeColumn.setMinWidth(JFrameMain.WIDTH_SIZE*150/1920);
		codeColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*151/1920);
		quantyColumn.setCellRenderer(cellRendererText);
		quantyColumn.setMinWidth(JFrameMain.WIDTH_SIZE*150/1920);
		quantyColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*151/1920);
		descriptionColumn.setCellRenderer(cellRendererText);
		descriptionColumn.setMinWidth(JFrameMain.WIDTH_SIZE*960/1920);
		descriptionColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*961/1920);
		priceUColumn.setCellRenderer(cellRenderMoney);
		priceUColumn.setMinWidth(JFrameMain.WIDTH_SIZE*250/1920);
		priceUColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*251/1920);
		priceTColumn.setCellRenderer(cellRenderMoney);
		priceUColumn.setMinWidth(JFrameMain.WIDTH_SIZE*250/1920);
		priceUColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*251/1920);
		jTable.getTableHeader().setReorderingAllowed(false);
		this.setViewportView(jTable);
		this.getVerticalScrollBar().setPreferredSize(new Dimension(10* JFrameMain.WIDTH_SIZE/1920, 0));
		this.getHorizontalScrollBar().setOpaque(false);
		this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(157, 195, 230);
			}
		});
		jTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if ((int)e.getKeyChar() == 8 || (int)e.getKeyChar() == 127) {
					if (jTable.getSelectedRow() != -1) {
						removeItemBill(jTable.getSelectedRow());
					}
				}
			}
		});
	}
	
	/**
	 * Metodo que remueve un item de la tabla
	 * @param index posicion del elemento
	 */
	private void removeItemBill(int index) {
		if (!this.products.isEmpty()) {
			totalItems--;
			if (totalItems == 0) {
				jTable.setRowHeight(JFrameMain.HEIGTH_SIZE / 4 + JFrameMain.HEIGTH_SIZE / 3 / 4);
			} else {
				jTable.setRowHeight((JFrameMain.HEIGTH_SIZE / 4 + JFrameMain.HEIGTH_SIZE / 3 / 4) / totalItems > 50
						? (JFrameMain.HEIGTH_SIZE / 4 + JFrameMain.HEIGTH_SIZE / 3 / 4) / totalItems
								: 50);
			}
			this.products.remove(index);
		}
	}

	/**
	 * Metodo que agrega items a la tabla
	 * @param product informacion del producto
	 */
	public void addItemToBill(Object[] product) {
		totalItems++;
		jTable.setRowHeight((JFrameMain.HEIGTH_SIZE / 4 + JFrameMain.HEIGTH_SIZE / 3 / 4) / totalItems > 50
				? (JFrameMain.HEIGTH_SIZE / 4 + JFrameMain.HEIGTH_SIZE / 3 / 4) / totalItems
				: 50);
		this.products.add(product);
	}

	/**
	 * Metodo que obtiene los productos de la tabla
	 * @return
	 */
	public ArrayList<Object[]> getProductsInBill() {
		return products;
	}
	
	/**
	 * Metodo que remueve todos los productos de la tabla
	 */
	public void removeAll() {
		for (int i = 0; i < products.size(); i++) {
			removeItemBill(i);
		}
	}

	/**
	 * Metodo que actualiza la tabla
	 * @param productsInBill productos de la tabla
	 */
	public void actualizeBill(ArrayList<Object[]> productsInBill) {
		this.products = productsInBill;
	}
}
