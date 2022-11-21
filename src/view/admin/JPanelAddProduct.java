package view.admin;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import controller.Controller;
import view.JFrameMain;
import view.utils.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ, DANIEL FELIPE SUAREZ BOHORQUEZ
 */
public class JPanelAddProduct extends JPanel {
	private GridBagConstraints gbc;
	private JPanel jPanelColumn1;
	private JPanel jPanelColumn2;
	private JPanel jPanelColumn3;
	private JLabel jLabelIdProduct;
	private JLabel jLabelNameProduct;
	private JLabel jLabelDescriptionProduct;
	private JLabel jLabelQuantityProduct;
	private JLabel jLabelTypeProduct;
	private JLabel jLabelPriceProduct;
	private JLabel jLabelWarrantyProduct;
	private JLabel jLabelIdSupplier;
	private JLabel jLabelNameSupplier;
	private JLabel jLabelPhoneSupplier;
	private JLabel jLabelAddresSupplier;
	private JLabel jLabelCitySupplier;
	private JLabel jLabelCountrySupplier;
	private JTextField jTextFieldIdProduct;
	private JTextField jTextFieldNameProduct;
	private JTextField jTextFieldDescriptionProduct;
	private JTextField jTextFieldQuantityProduct;
	private JButton jButtonAddQuantity;
	private JButton jButtonSubstractQuantity;
	private JComboBox<String> jComboBoxTypeProduct;
	private JTextField jTextFieldPriceProduct;
	private JTextField jTextFieldWarrantyProduct;
	private JComboBox<String> jComboBoxWarrantyProduct;
	private JTextField jTextFieldIdSupplier;
	private JTextField jTextFieldNameSupplier;
	private JTextField jTextFieldPhoneSupplier;
	private JTextField jTextFieldAddresSupplier;
	private JTextField jTextFieldCitySupplier;
	private JTextField jTextFieldCountrySupplier;
	private JButton jButtonAddProduct;
	private JButton jButtonBack;
	private JTextField jTextFieldImagePath;
	private JLabel jLabelImageProduct;
	private JButton buttonFileChooser;
	private JFileChooser chooser;

	/**
	 * Constructor de JPanelAddProduct
	 */
	public JPanelAddProduct() {
		super(new GridLayout(1, 3));
		this.gbc = new GridBagConstraints();
		this.jPanelColumn1 = new JPanel(new GridBagLayout());
		this.jPanelColumn2 = new JPanel(new GridBagLayout());
		this.jPanelColumn3 = new JPanel(new GridBagLayout());
		this.jLabelIdProduct = new JLabel("Id Producto:");
		this.jLabelNameProduct = new JLabel("Nombre de Producto:");
		this.jLabelDescriptionProduct = new JLabel("Descripcion del Producto:");
		this.jLabelQuantityProduct = new JLabel("Cantidad:");
		this.jLabelTypeProduct = new JLabel("Tipo de Producto:");
		this.jLabelPriceProduct = new JLabel("Precio por unidad:");
		this.jLabelWarrantyProduct = new JLabel("Garantia:");
		this.jLabelIdSupplier = new JLabel("Id Proovedor:");
		this.jLabelNameSupplier = new JLabel("Nombre Proovedor:");
		this.jLabelPhoneSupplier = new JLabel("Telefono Proovedor");
		this.jLabelAddresSupplier = new JLabel("Direccion Proovedor");
		this.jLabelCitySupplier = new JLabel("Ciudad Proovedor");
		this.jLabelCountrySupplier = new JLabel("Pais Proovedor");
		this.jTextFieldIdProduct = new JTextField();
		this.jTextFieldNameProduct = new JTextField();
		this.jTextFieldDescriptionProduct = new JTextField();
		this.jTextFieldQuantityProduct = new JTextField("0");
		this.jButtonAddQuantity = new JButton();
		this.jButtonSubstractQuantity = new JButton();
		this.jComboBoxTypeProduct = new JComboBox<>();
		this.jTextFieldPriceProduct = new JTextField();
		this.jTextFieldWarrantyProduct = new JTextField();
		this.jComboBoxWarrantyProduct = new JComboBox<>();
		this.jTextFieldIdSupplier = new JTextField();
		this.jTextFieldNameSupplier = new JTextField();
		this.jTextFieldPhoneSupplier = new JTextField();
		this.jTextFieldAddresSupplier = new JTextField();
		this.jTextFieldCitySupplier = new JTextField();
		this.jTextFieldCountrySupplier = new JTextField();
		this.jButtonAddProduct = new JButton("Añadir");
		this.jButtonBack = new JButton("Atras");
		this.jTextFieldImagePath = new JTextField();
		this.jLabelImageProduct = new JLabel("Imagen del producto");
		this.buttonFileChooser = new JButton(new ImageIcon(
				new ImageIcon("res/imageIcon.png").getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		this.chooser = new JFileChooser();
		init();
	}

	/**
	 * Metodo que inicializa las propiedades de este panel
	 */
	private void init() {
		setOpaque(false);
		initPropComponents();
		jPanelColumn1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(48, 116, 180, 50)));
		jPanelColumn2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(48, 116, 180, 50)));
		this.add(jPanelColumn1);
		this.add(jPanelColumn2);
		this.add(jPanelColumn3);
		gbc.fill = 1;
		addComponentsColumn1();
		addComponentsColumn2();
		addComponentsColumn3();
	}

	/**
	 * Metodo que agrega los componentes a la columna 3
	 */
	private void addComponentsColumn3() {
		this.gbc.insets = new Insets(10, 30, 0, 30);
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 0;
		this.jPanelColumn3.add(jLabelAddresSupplier, gbc);
		gbc.gridy = 1;
		gbc.insets.bottom = 50;
		this.jPanelColumn3.add(jTextFieldAddresSupplier, gbc);
		gbc.gridy = 2;
		gbc.insets.bottom = 0;
		this.jPanelColumn3.add(jLabelCitySupplier, gbc);
		gbc.gridy = 3;
		gbc.insets.bottom = 50;
		this.jPanelColumn3.add(jTextFieldCitySupplier, gbc);
		gbc.gridy = 4;
		gbc.insets.bottom = 0;
		this.jPanelColumn3.add(jLabelCountrySupplier, gbc);
		gbc.gridy = 5;
		gbc.insets.bottom = 50;
		this.jPanelColumn3.add(jTextFieldCountrySupplier, gbc);
		gbc.gridy = 6;
		gbc.insets.bottom = 0;
		this.jPanelColumn3.add(jLabelImageProduct, gbc);
		gbc.gridy = 7;
		gbc.insets.bottom = 50;
		gbc.insets.right = 5;
		gbc.gridwidth = 1;
		gbc.fill = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		this.jPanelColumn3.add(buttonFileChooser, gbc);
		gbc.insets.left = 0;
		gbc.insets.right = 30;
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.fill = 1;
		this.jPanelColumn3.add(jTextFieldImagePath, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets.top = 50;
		gbc.insets.bottom = 0;
		gbc.insets.left = 30;
		this.jPanelColumn3.add(jButtonAddProduct, gbc);
		gbc.insets.top = 5;
		gbc.gridy = 9;
		this.jPanelColumn3.add(jButtonBack, gbc);
	}

	/**
	 * Metodo que agrega los componentes a la columna 2
	 */
	private void addComponentsColumn2() {
		this.gbc.insets = new Insets(10, 30, 0, 30);
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.gridy = 0;
		this.jPanelColumn2.add(jLabelPriceProduct, gbc);
		gbc.gridy = 1;
		gbc.insets.bottom = 50;
		this.jPanelColumn2.add(jTextFieldPriceProduct, gbc);
		gbc.gridy = 2;
		gbc.insets.bottom = 0;
		this.jPanelColumn2.add(jLabelWarrantyProduct, gbc);
		gbc.gridy = 3;
		gbc.insets.bottom = 50;
		gbc.gridwidth = 1;
		gbc.insets.right = 0;
		this.jPanelColumn2.add(jTextFieldWarrantyProduct, gbc);
		gbc.gridx = 1;
		gbc.insets.right = 30;
		gbc.insets.left = 0;
		gbc.weightx = 0;
		this.jPanelColumn2.add(jComboBoxWarrantyProduct, gbc);
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		gbc.insets.left = 30;
		gbc.gridy = 4;
		gbc.gridx = 0;
		gbc.insets.bottom = 0;
		this.jPanelColumn2.add(jLabelIdSupplier, gbc);
		gbc.gridy = 5;
		gbc.insets.bottom = 50;
		this.jPanelColumn2.add(jTextFieldIdSupplier, gbc);
		gbc.gridy = 6;
		gbc.insets.bottom = 0;
		this.jPanelColumn2.add(jLabelNameSupplier, gbc);
		gbc.gridy = 7;
		gbc.insets.bottom = 50;
		this.jPanelColumn2.add(jTextFieldNameSupplier, gbc);
		gbc.gridy = 8;
		gbc.insets.bottom = 0;
		this.jPanelColumn2.add(jLabelPhoneSupplier, gbc);
		gbc.gridy = 9;
		gbc.insets.bottom = 50;
		this.jPanelColumn2.add(jTextFieldPhoneSupplier, gbc);
	}

	/**
	 * Metodo que agrega los componentes a la columna 1
	 */
	private void addComponentsColumn1() {
		this.gbc.insets = new Insets(10, 30, 0, 30);
		gbc.weightx = 1;
		gbc.gridwidth = 2;
		this.jPanelColumn1.add(jLabelIdProduct, gbc);
		gbc.gridy = 1;
		gbc.insets.bottom = 50;
		this.jPanelColumn1.add(jTextFieldIdProduct, gbc);
		gbc.gridy = 2;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jLabelNameProduct, gbc);
		gbc.gridy = 3;
		gbc.insets.bottom = 50;
		this.jPanelColumn1.add(jTextFieldNameProduct, gbc);
		gbc.gridy = 4;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jLabelDescriptionProduct, gbc);
		gbc.gridy = 5;
		gbc.insets.bottom = 50;
		this.jPanelColumn1.add(jTextFieldDescriptionProduct, gbc);
		gbc.gridy = 6;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jLabelQuantityProduct, gbc);
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.insets.right = 0;
		gbc.insets.bottom = 50;
		jTextFieldQuantityProduct.setPreferredSize(new Dimension(200, 30));
		this.jPanelColumn1.add(jTextFieldQuantityProduct, gbc);
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.fill = 0;
		gbc.insets.right = 300;
		gbc.insets.bottom = 0;
		jButtonAddQuantity.setPreferredSize(new Dimension(30, 15));
		this.jPanelColumn1.add(jButtonAddQuantity, gbc);
		gbc.insets.top = 0;
		gbc.gridy = 8;
		gbc.insets.bottom = 50;
		jButtonSubstractQuantity.setPreferredSize(new Dimension(30, 15));
		this.jPanelColumn1.add(jButtonSubstractQuantity, gbc);
		gbc.insets.right = 30;
		gbc.insets.top = 10;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.fill = 1;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jLabelTypeProduct, gbc);
		gbc.gridy = 10;
		gbc.insets.bottom = 50;
		this.jPanelColumn1.add(jComboBoxTypeProduct, gbc);
	}

	/**
	 * Metodo que inicializa las propiedades de los componentes
	 */
	private void initPropComponents() {
		this.jPanelColumn1.setOpaque(false);
		this.jPanelColumn2.setOpaque(false);
		this.jPanelColumn3.setOpaque(false);

		Font font = new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920);
		this.jLabelImageProduct.setFont(font);
		this.jLabelIdProduct.setFont(font);
		this.jLabelNameProduct.setFont(font);
		this.jLabelDescriptionProduct.setFont(font);
		this.jLabelQuantityProduct.setFont(font);
		this.jLabelTypeProduct.setFont(font);
		this.jLabelPriceProduct.setFont(font);
		this.jLabelWarrantyProduct.setFont(font);
		this.jLabelIdSupplier.setFont(font);
		this.jLabelNameSupplier.setFont(font);
		this.jLabelPhoneSupplier.setFont(font);
		this.jLabelAddresSupplier.setFont(font);
		this.jLabelCitySupplier.setFont(font);
		this.jLabelCountrySupplier.setFont(font);
		Color color = new Color(47, 85, 151);
		this.jLabelIdProduct.setForeground(color);
		this.jLabelNameProduct.setForeground(color);
		this.jLabelDescriptionProduct.setForeground(color);
		this.jLabelQuantityProduct.setForeground(color);
		this.jLabelTypeProduct.setForeground(color);
		this.jLabelPriceProduct.setForeground(color);
		this.jLabelWarrantyProduct.setForeground(color);
		this.jLabelIdSupplier.setForeground(color);
		this.jLabelNameSupplier.setForeground(color);
		this.jLabelPhoneSupplier.setForeground(color);
		this.jLabelAddresSupplier.setForeground(color);
		this.jLabelCitySupplier.setForeground(color);
		this.jLabelCountrySupplier.setForeground(color);
		this.jLabelImageProduct.setForeground(color);

		font = new Font("Arial", Font.ITALIC, 25 * JFrameMain.WIDTH_SIZE / 1920);

		this.jTextFieldQuantityProduct.setEditable(false);

		this.jTextFieldIdProduct.setFont(font);
		this.jTextFieldNameProduct.setFont(font);
		this.jTextFieldDescriptionProduct.setFont(font);
		this.jTextFieldQuantityProduct.setFont(font);
		this.jTextFieldImagePath.setFont(new Font("Arial", Font.PLAIN, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.jComboBoxTypeProduct.setFont(font);
		this.jComboBoxTypeProduct.addItem("Componente");
		this.jComboBoxTypeProduct.addItem("PC");
		this.jComboBoxTypeProduct.addItem("Celulares");
		this.jComboBoxTypeProduct.addItem("Laptop");
		this.jComboBoxTypeProduct.addItem("Accesorios");
		this.jTextFieldPriceProduct.setFont(font);
		this.jTextFieldWarrantyProduct.setFont(font);
		this.jComboBoxWarrantyProduct.setFont(font);
		this.jComboBoxWarrantyProduct.addItem("Año(s)");
		this.jComboBoxWarrantyProduct.addItem("Mes(es)");
		this.jTextFieldIdSupplier.setFont(font);
		this.jTextFieldNameSupplier.setFont(font);
		this.jTextFieldPhoneSupplier.setFont(font);
		this.jTextFieldAddresSupplier.setFont(font);
		this.jTextFieldCitySupplier.setFont(font);
		this.jTextFieldCountrySupplier.setFont(font);
		this.jTextFieldIdProduct.setForeground(Color.BLACK);
		this.jTextFieldNameProduct.setForeground(Color.BLACK);
		this.jTextFieldDescriptionProduct.setForeground(Color.BLACK);
		this.jTextFieldQuantityProduct.setForeground(Color.BLACK);
		this.jButtonAddQuantity.setBackground(new Color(22, 205, 50));
		this.jButtonAddQuantity.setBorderPainted(false);
		this.jButtonSubstractQuantity.setBackground(new Color(247, 54, 6));
		this.jButtonSubstractQuantity.setBorderPainted(false);
		this.jComboBoxTypeProduct.setForeground(Color.BLACK);
		this.jTextFieldPriceProduct.setForeground(Color.BLACK);
		this.jTextFieldWarrantyProduct.setForeground(Color.BLACK);
		this.jComboBoxWarrantyProduct.setForeground(Color.BLACK);
		this.jTextFieldIdSupplier.setForeground(Color.BLACK);
		this.jTextFieldNameSupplier.setForeground(Color.BLACK);
		this.jTextFieldPhoneSupplier.setForeground(Color.BLACK);
		this.jTextFieldAddresSupplier.setForeground(Color.BLACK);
		this.jTextFieldCitySupplier.setForeground(Color.BLACK);
		this.jTextFieldCountrySupplier.setForeground(Color.BLACK);
		this.jTextFieldImagePath.setForeground(Color.BLACK);

		this.jTextFieldImagePath.setOpaque(false);
		this.jTextFieldIdProduct.setOpaque(false);
		this.jTextFieldNameProduct.setOpaque(false);
		this.jTextFieldDescriptionProduct.setOpaque(false);
		this.jTextFieldQuantityProduct.setOpaque(false);
		this.jComboBoxTypeProduct.setOpaque(false);
		this.jTextFieldPriceProduct.setOpaque(false);
		this.jTextFieldWarrantyProduct.setOpaque(false);
		this.jComboBoxWarrantyProduct.setOpaque(false);
		this.jTextFieldIdSupplier.setOpaque(false);
		this.jTextFieldNameSupplier.setOpaque(false);
		this.jTextFieldPhoneSupplier.setOpaque(false);
		this.jTextFieldAddresSupplier.setOpaque(false);
		this.jTextFieldCitySupplier.setOpaque(false);
		this.jTextFieldCountrySupplier.setOpaque(false);

		LineBorder border = new LineBorder(Color.BLACK, 3);
		this.jTextFieldImagePath.setBorder(border);
		this.jTextFieldIdProduct.setBorder(border);
		this.jTextFieldNameProduct.setBorder(border);
		this.jTextFieldDescriptionProduct.setBorder(border);
		this.jTextFieldQuantityProduct.setBorder(border);
		this.jComboBoxTypeProduct.setBorder(border);
		this.jTextFieldPriceProduct.setBorder(border);
		this.jTextFieldWarrantyProduct.setBorder(border);
		this.jComboBoxWarrantyProduct.setBorder(border);
		this.jTextFieldIdSupplier.setBorder(border);
		this.jTextFieldNameSupplier.setBorder(border);
		this.jTextFieldPhoneSupplier.setBorder(border);
		this.jTextFieldAddresSupplier.setBorder(border);
		this.jTextFieldCitySupplier.setBorder(border);
		this.jTextFieldCountrySupplier.setBorder(border);
		this.jTextFieldImagePath.setEditable(false);

		this.jButtonAddProduct.setFont(font);
		this.jButtonAddProduct.setBackground(new Color(49, 116, 180));
		this.jButtonAddProduct.setForeground(Color.WHITE);
		this.jButtonAddProduct.setBorder(border);
		this.jButtonAddProduct.setFocusPainted(false);
		this.jButtonAddProduct.setRolloverEnabled(false);
		this.jButtonAddProduct.addActionListener(Controller.getInstance());
		this.jButtonAddProduct.setActionCommand(Constants.COMMAND_ADD_NEW_PRODUCT);

		this.buttonFileChooser.setContentAreaFilled(false);
		this.buttonFileChooser.setBorder(border);
		this.buttonFileChooser.setRolloverEnabled(false);
		this.buttonFileChooser.setFocusable(false);
		this.buttonFileChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chooser.showOpenDialog(JFrameMain.getInstance());
				jTextFieldImagePath.setText(chooser.getSelectedFile().getPath());
			}
		});

		this.jButtonBack.addActionListener(JFrameMain.getInstance());
		this.jButtonBack.setActionCommand(Constants.COMMAND_BUTTON_BACK_ADD_PRODUCT);
		this.jButtonBack.setFont(font);
		this.jButtonBack.setBackground(new Color(49, 116, 180));
		this.jButtonBack.setForeground(Color.WHITE);
		this.jButtonBack.setBorder(border);
		this.jButtonBack.setFocusPainted(false);
		this.jButtonBack.setRolloverEnabled(false);
	}

	/**
	 * Metodo que obtiene la informacion del producto
	 * 
	 * @return informacion del producto
	 */
	public String[] getInfoNewProduct() {
		return new String[] { 
				jTextFieldIdProduct.getText(), 
				jTextFieldNameProduct.getText(),
				jTextFieldDescriptionProduct.getText(), 
				jTextFieldQuantityProduct.getText(),
				jComboBoxTypeProduct.getSelectedItem() + ""
				, jTextFieldPriceProduct.getText(),
				jTextFieldWarrantyProduct.getText(), 
				jComboBoxWarrantyProduct.getSelectedItem() + "",
				jTextFieldIdSupplier.getText(), 
				jTextFieldNameSupplier.getText(), 
				jTextFieldPhoneSupplier.getText(),
				jTextFieldAddresSupplier.getText(), 
				jTextFieldCitySupplier.getText(),
				jTextFieldCountrySupplier.getText(), 
				jTextFieldImagePath.getText() };
	}

	/**
	 * Metodo que resalta el campo de texto que se pida
	 * 
	 * @param index posicion del campo de texto
	 * @param color color de resaltado
	 */
	public void underlineTo(int index, int color) {
		Border borderRed = new LineBorder(Color.RED, 3);
		Border borderBlack = new LineBorder(Color.BLACK, 3);
		switch (index) {
		case 0:
			if (color == 0) {
				jTextFieldIdProduct.setBorder(borderRed);
			} else {
				jTextFieldIdProduct.setBorder(borderBlack);
			}
			break;
		case 1:
			if (color == 0) {
				jTextFieldNameProduct.setBorder(borderRed);
			} else {
				jTextFieldNameProduct.setBorder(borderBlack);
			}
			break;
		case 2:
			if (color == 0) {
				jTextFieldDescriptionProduct.setBorder(borderRed);
			} else {
				jTextFieldDescriptionProduct.setBorder(borderBlack);
			}
			break;
		case 3:
			if (color == 0) {
				jTextFieldQuantityProduct.setBorder(borderRed);
			} else {
				jTextFieldQuantityProduct.setBorder(borderBlack);
			}
			break;
		case 5:
			if (color == 0) {
				jTextFieldPriceProduct.setBorder(borderRed);
			} else {
				jTextFieldPriceProduct.setBorder(borderBlack);
			}
			break;
		case 6:
			if (color == 0) {
				jTextFieldWarrantyProduct.setBorder(borderRed);
			} else {
				jTextFieldWarrantyProduct.setBorder(borderBlack);
			}
			break;
		case 8:
			if (color == 0) {
				jTextFieldNameSupplier.setBorder(borderRed);
			} else {
				jTextFieldNameSupplier.setBorder(borderBlack);
			}
			break;
		case 9:
			if (color == 0) {
				jTextFieldNameSupplier.setBorder(borderRed);
			} else {
				jTextFieldNameSupplier.setBorder(borderBlack);
			}
			break;
		case 10:
			if (color == 0) {
				jTextFieldPhoneSupplier.setBorder(borderRed);
			} else {
				jTextFieldPhoneSupplier.setBorder(borderBlack);
			}
			break;
		case 11:
			if (color == 0) {
				jTextFieldAddresSupplier.setBorder(borderRed);
			} else {
				jTextFieldAddresSupplier.setBorder(borderBlack);
			}
			break;
		case 12:
			if (color == 0) {
				jTextFieldCitySupplier.setBorder(borderRed);
			} else {
				jTextFieldCitySupplier.setBorder(borderBlack);
			}
			break;
		case 13:
			if (color == 0) {
				jTextFieldCountrySupplier.setBorder(borderRed);
			} else {
				jTextFieldCountrySupplier.setBorder(borderBlack);
			}
			break;
		case 14:
			if (color == 0) {
				jTextFieldImagePath.setBorder(borderRed);
			} else {
				jTextFieldImagePath.setBorder(borderBlack);
			}
			break;
		}
	}

	/**
	 * Metodo que pone en blanco las cajas de texto de agregar producto
	 */
	public void putBlankAddProductText() {
		jTextFieldIdProduct.setText("");
		jTextFieldNameProduct.setText("");
		jTextFieldDescriptionProduct.setText("");
		jTextFieldQuantityProduct.setText("");
		jTextFieldPriceProduct.setText("");
		jTextFieldWarrantyProduct.setText("");
		jTextFieldNameSupplier.setText("");
		jTextFieldNameSupplier.setText("");
		jTextFieldPhoneSupplier.setText("");
		jTextFieldAddresSupplier.setText("");
		jTextFieldCitySupplier.setText("");
		jTextFieldCountrySupplier.setText("");
		jTextFieldImagePath.setText("");
	}
}
