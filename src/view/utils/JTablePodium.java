package view.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class JTablePodium extends JScrollPane {

	private static final long serialVersionUID = 1L;
	private JTable jTable;
	private ArrayList<Object[]> infoTable;
	private String[] columnNames;

	/**
	 * Constructor de JTablePodium
	 * @param info
	 * @param type
	 */
	public JTablePodium(ArrayList<Object[]> info, int type) {
		super();
		this.infoTable = info;
		this.columnNames = new String[] { "Puesto", "Nombre", "Ventas" };
		init(type);
	}

	/**
	 * Metodo que inicia esta tabla
	 * @param type si es tabla de cajeros o de productos
	 */
	private void init(int type) {
		this.setOpaque(false);
		this.getViewport().setOpaque(false);
		this.getVerticalScrollBar().setOpaque(false);
		this.getHorizontalScrollBar().setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder());

		DefaultTableCellRenderer cellRenderPuesto = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void setValue(Object value) {
				setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
				setForeground(Color.BLACK);
				setOpaque(false);
				super.setValue(value);
			}
		};
		cellRenderPuesto.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer cellRendererText = new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			protected void setValue(Object value) {
				setFont(new Font("Arial", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE/1920));
				setForeground(Color.BLACK);
				setOpaque(false);
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
				return infoTable.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				return infoTable.get(row) [col];
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
				infoTable.get(row)[column] = aValue;
			}
		};

		jTable = new JTable(dataModel);
		jTable.setOpaque(false);
		jTable.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE/1920));
		jTable.getTableHeader().setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 25* JFrameMain.WIDTH_SIZE/1920));
		jTable.getTableHeader().setBorder(new LineBorder(Color.BLACK, 1));
		jTable.getTableHeader().setForeground(Color.WHITE);
		
		if (type == 0) {
			jTable.getTableHeader().setBackground(new Color(48, 116, 180));
		}else {
			jTable.getTableHeader().setBackground(new Color(249, 142, 73));
		}

		TableColumn puestoColumn = jTable.getColumn("Puesto");
		TableColumn nameColumn = jTable.getColumn("Nombre");
		TableColumn salesColumn = jTable.getColumn("Ventas");
		
		puestoColumn.setCellRenderer(cellRenderPuesto);
		puestoColumn.setMinWidth(JFrameMain.WIDTH_SIZE*110/1920);
		puestoColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*111/1920);
		nameColumn.setCellRenderer(cellRendererText);
		nameColumn.setMinWidth(JFrameMain.WIDTH_SIZE*370/1920);
		nameColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*371/1920);
		salesColumn.setCellRenderer(cellRenderPuesto);
		salesColumn.setMinWidth(JFrameMain.WIDTH_SIZE*110/1920);
		salesColumn.setMaxWidth(JFrameMain.WIDTH_SIZE*111/1920);
		
		jTable.getTableHeader().setReorderingAllowed(false);
		jTable.setBorder(new LineBorder(Color.BLACK,1));
		
		this.setViewportView(jTable);
		this.getVerticalScrollBar().setPreferredSize(new Dimension(10* JFrameMain.WIDTH_SIZE/1920, 0));
		this.getHorizontalScrollBar().setOpaque(false);
		this.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(157, 195, 230);
			}
		});
		jTable.setRowHeight(60);
	}

}
