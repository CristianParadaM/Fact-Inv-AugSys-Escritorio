package view.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
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
public class JTableWarranty extends JScrollPane {

	private static final int HEIGHT_ROW = 300* JFrameMain.HEIGTH_SIZE/1080;
	private static final String PRECIO_TOTAL_$ = "Precio T. $.";
	private static final String PRECIO_UNITARIO_$ = "Precio Un. $.";
	private static final String WARRANTY = "Garantia";
	private static final String DESCRIPCION = "Descripci�n";
	private static final String CANTIDAD = "Cantidad";
	private static final String CODIGO = "C�digo";
	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private ArrayList<Object[]> products;
	private String[] columnNames;
	private int totalItems;

	/**
	 * Constructor de JTableWarranty
	 * @param products productos de la tabla
	 */
	public JTableWarranty(ArrayList<Object[]> products) {
		super();
		this.products = products;
		this.columnNames = new String[] { CODIGO, CANTIDAD, DESCRIPCION, WARRANTY, PRECIO_UNITARIO_$, PRECIO_TOTAL_$ };
		this.totalItems = products.size();
		init();
	}

	/**
	 * Metodo que inicia esta tabla
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

		DefaultTableCellRenderer cellRenderWarranty = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("unchecked")
			@Override
			protected void setValue(Object value) {
				setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
				if (((String) value).equals("Activa")) {
					setForeground(Color.GREEN);
					Font font = getFont();
					@SuppressWarnings("rawtypes")
					Map attributes = font.getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					setFont(font.deriveFont(attributes));
				} else {
					setForeground(Color.RED);
				}
				setOpaque(false);
				super.setValue(value);
			}
		};
		cellRenderWarranty.setHorizontalAlignment(JLabel.CENTER);

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
		TableColumn warrantyColumn = jTable.getColumn(WARRANTY);
		TableColumn priceUColumn = jTable.getColumn(PRECIO_UNITARIO_$);
		TableColumn priceTColumn = jTable.getColumn(PRECIO_TOTAL_$);

		codeColumn.setCellRenderer(cellRendererText);
		codeColumn.setMinWidth(JFrameMain.WIDTH_SIZE * 120 / 1920);
		codeColumn.setMaxWidth(JFrameMain.WIDTH_SIZE * 121 / 1920);
		quantyColumn.setCellRenderer(cellRendererText);
		quantyColumn.setMinWidth(JFrameMain.WIDTH_SIZE * 150 / 1920);
		quantyColumn.setMaxWidth(JFrameMain.WIDTH_SIZE * 151 / 1920);
		descriptionColumn.setCellRenderer(cellRendererText);
		descriptionColumn.setMinWidth(JFrameMain.WIDTH_SIZE * 800 / 1920);
		descriptionColumn.setMaxWidth(JFrameMain.WIDTH_SIZE * 801 / 1920);
		warrantyColumn.setCellRenderer(cellRenderWarranty);
		warrantyColumn.setMinWidth(JFrameMain.WIDTH_SIZE * 150 / 1920);
		warrantyColumn.setMaxWidth(JFrameMain.WIDTH_SIZE * 151 / 1920);
		priceUColumn.setCellRenderer(cellRenderMoney);
		priceUColumn.setMinWidth(JFrameMain.WIDTH_SIZE * 180 / 1920);
		priceUColumn.setMaxWidth(JFrameMain.WIDTH_SIZE * 181 / 1920);
		priceTColumn.setCellRenderer(cellRenderMoney);
		priceUColumn.setMinWidth(JFrameMain.WIDTH_SIZE * 200 / 1920);
		priceUColumn.setMaxWidth(JFrameMain.WIDTH_SIZE * 201 / 1920);
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.setRowHeight(HEIGHT_ROW / totalItems > 50 ? HEIGHT_ROW / totalItems : 50);
		this.setViewportView(jTable);
		this.getVerticalScrollBar().setPreferredSize(new Dimension(10* JFrameMain.WIDTH_SIZE/1920, 0));
		this.getHorizontalScrollBar().setOpaque(false);
		this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(157, 195, 230);
			}
		});
	}

	/**
	 * Metodo que obtiene los productos de esta tabla
	 * @return lista con la informacion de los productos
	 */
	public ArrayList<Object[]> getProductsInTableWarranty() {
		return products;
	}

	/**
	 * Metodo que actualiza la lista 
	 * @param productsInBill lista de productos
	 */
	public void actualizeBill(ArrayList<Object[]> productsInBill) {
		this.products = productsInBill;
	}
}
