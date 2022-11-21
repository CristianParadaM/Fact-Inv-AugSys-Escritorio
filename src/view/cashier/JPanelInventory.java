package view.cashier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import model.TextPrompt;
import view.JFrameMain;
import view.utils.Constants;

public class JPanelInventory extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	private JLabel jlabelTittle;
	private JTextField jTextFieldSearch;
	private JButton jbuttonDeleteAll;
	private JButton buttonBack;
	private JButton buttonFilter;
	private JButton buttonSearch;
	private JPanelProducts jPanelProducts;
	private JPopupMenu jPopupMenu;
	// filters
	private JLabel jLabelTittleType;
	private JLabel jLabelTittlePrice;
	private JRadioButton radioButtonComponent;
	private JRadioButton radioButtonPhone;
	private JRadioButton radioButtonLaptop;
	private JRadioButton radioButtonPC;
	private JRadioButton radioButtonAccesory;
	private ButtonGroup buttonGroupType;

	private JRadioButton radioButtonPrice0_500k;
	private JRadioButton radioButtonPrice500k_1M;
	private JRadioButton radioButtonPrice1M_5M;
	private JRadioButton radioButtonPrice5M_More;
	private JButton putOffFilters;
	private ButtonGroup buttonGroupPrice;

	/**
	 * Constructor de JPanelInventory
	 * @param listProducts lista de productos
	 */
	public JPanelInventory(ArrayList<Object[]> listProducts) {
		super(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.jbuttonDeleteAll = new JButton("  X  ");
		this.jlabelTittle = new JLabel("Agregar Productos", JLabel.CENTER);
		this.jTextFieldSearch = new JTextField();
		this.buttonBack = new JButton(
				new ImageIcon(new ImageIcon("res/back.png").getImage().getScaledInstance(100, 30, Image.SCALE_SMOOTH)));
		this.buttonFilter = new JButton(new ImageIcon(new ImageIcon("res/filtro.png").getImage().getScaledInstance(
				100 * JFrameMain.WIDTH_SIZE / 1920, 50 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH)));
		this.buttonSearch = new JButton(new ImageIcon(new ImageIcon("res/searchIcon.png").getImage().getScaledInstance(
				30 * JFrameMain.WIDTH_SIZE / 1920, 30 * JFrameMain.HEIGTH_SIZE / 1080, Image.SCALE_SMOOTH)));
		this.jPanelProducts = new JPanelProducts(listProducts);
		this.jPopupMenu = new JPopupMenu();

		this.jLabelTittleType = new JLabel("Tipo", JLabel.CENTER);
		this.jLabelTittlePrice = new JLabel("Precio", JLabel.CENTER);

		this.radioButtonComponent = new JRadioButton("Componente");
		this.radioButtonPhone = new JRadioButton("Celulares");
		this.radioButtonLaptop = new JRadioButton("Laptop");
		this.radioButtonPC = new JRadioButton("PC");
		this.radioButtonAccesory = new JRadioButton("Accesorio");

		this.radioButtonPrice0_500k = new JRadioButton("0 - 500k");
		this.radioButtonPrice500k_1M = new JRadioButton("500k - 1M");
		this.radioButtonPrice1M_5M = new JRadioButton("1M - 5M");
		this.radioButtonPrice5M_More = new JRadioButton("5M+");
		this.buttonGroupType = new ButtonGroup();
		this.buttonGroupPrice = new ButtonGroup();
		this.putOffFilters = new JButton("x");
		init();
	}

	/**
	 * Metodo que inicializa este panel
	 */
	private void init() {
		this.setOpaque(false);
		initPropComponents();
		initPropFilters();
		gbc.fill = 1;
		gbc.gridwidth = 3;
		this.add(jlabelTittle, gbc);
		gbc.insets.top = 50 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.left = 30 * JFrameMain.WIDTH_SIZE / 1920;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.gridwidth = 1;
		this.add(jTextFieldSearch, gbc);
		gbc.weightx = 0;
		gbc.gridx = 1;
		this.add(buttonFilter, gbc);
		gbc.gridx = 2;
		gbc.insets.right = 30 * JFrameMain.WIDTH_SIZE / 1920;
		this.add(buttonBack, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		gbc.weighty = 1;
		gbc.insets = new Insets(25 * JFrameMain.HEIGTH_SIZE / 1080, 30 * JFrameMain.WIDTH_SIZE / 1920, 10 * JFrameMain.HEIGTH_SIZE / 1080,
				30 * JFrameMain.WIDTH_SIZE / 1920);
		this.add(jPanelProducts, gbc);
	}

	/**
	 * Metodo que inicializa las propiedadeds de los componentes
	 */
	private void initPropComponents() {
		this.buttonSearch.setRolloverEnabled(false);
		this.buttonSearch.setFocusable(false);
		this.buttonSearch.setContentAreaFilled(false);
		this.buttonSearch.setBorderPainted(false);
		this.buttonSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.buttonBack.setRolloverEnabled(false);
		this.buttonBack.setFocusable(false);
		this.buttonBack.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		this.buttonBack.setBackground(new Color(157, 195, 230));
		this.buttonBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.buttonBack.addActionListener(JFrameMain.getInstance());
		this.buttonBack.setActionCommand(Constants.COMMAND_BUTTON_BACK_HISTORY);

		this.jlabelTittle.setForeground(Color.WHITE);
		this.jlabelTittle.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 55 * JFrameMain.WIDTH_SIZE / 1920));
		this.jbuttonDeleteAll.setForeground(Color.BLACK);
		this.jbuttonDeleteAll.setFont(new Font("Arial", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));
		this.jbuttonDeleteAll.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, new Color(48, 116, 180)));
		this.jbuttonDeleteAll.setContentAreaFilled(false);
		this.jbuttonDeleteAll.setFocusable(false);
		this.jbuttonDeleteAll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jbuttonDeleteAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jTextFieldSearch.setText("");
				filtre(jTextFieldSearch.getText());
			}
		});
		this.jTextFieldSearch.setForeground(Color.BLACK);
		this.jTextFieldSearch.setOpaque(false);
		this.jTextFieldSearch.setFont(new Font("Arial", Font.ITALIC, 25 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldSearch.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		this.jTextFieldSearch.setLayout(new BorderLayout());
		this.jTextFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (jTextFieldSearch.getText().isEmpty() && (int) e.getKeyChar() != 8) {
					jTextFieldSearch.setText("\t" + jTextFieldSearch.getText());
				}
				filtre(jTextFieldSearch.getText() + e.getKeyChar() + "");
			}

		});
		TextPrompt textPrompt = new TextPrompt(
				"           Digite el nombre del producto que desea buscar (Ejemplo: Memoria Ram DDR 8GB)",
				jTextFieldSearch);
		textPrompt.changeAlpha(0.35f);
		textPrompt.changeStyle(Font.ITALIC);
		this.jTextFieldSearch.add(jbuttonDeleteAll, BorderLayout.EAST);
		this.jTextFieldSearch.add(buttonSearch, BorderLayout.WEST);

		this.buttonFilter.setRolloverEnabled(false);
		this.buttonFilter.setFocusable(false);
		this.buttonFilter.setContentAreaFilled(false);
		this.buttonFilter.setBorderPainted(false);
		this.buttonFilter.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.buttonFilter.addActionListener(JFrameMain.getInstance());
		this.buttonFilter.setActionCommand(Constants.COMMAND_BUTTON_FILTER);

		this.jLabelTittleType.setForeground(new Color(48, 116, 180));
		this.jLabelTittlePrice.setForeground(new Color(48, 116, 180));
		this.jLabelTittleType.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelTittlePrice.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));

		this.radioButtonComponent.setForeground(Color.BLACK);
		this.radioButtonPhone.setForeground(Color.BLACK);
		this.radioButtonLaptop.setForeground(Color.BLACK);
		this.radioButtonPC.setForeground(Color.BLACK);
		this.radioButtonAccesory.setForeground(Color.BLACK);
		this.radioButtonPrice0_500k.setForeground(Color.BLACK);
		this.radioButtonPrice500k_1M.setForeground(Color.BLACK);
		this.radioButtonPrice1M_5M.setForeground(Color.BLACK);
		this.radioButtonPrice5M_More.setForeground(Color.BLACK);

		this.radioButtonComponent.setOpaque(false);
		this.radioButtonPhone.setOpaque(false);
		this.radioButtonLaptop.setOpaque(false);
		this.radioButtonPC.setOpaque(false);
		this.radioButtonAccesory.setOpaque(false);
		this.radioButtonPrice0_500k.setOpaque(false);
		this.radioButtonPrice500k_1M.setOpaque(false);
		this.radioButtonPrice1M_5M.setOpaque(false);
		this.radioButtonPrice5M_More.setOpaque(false);

		this.radioButtonComponent
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonPhone
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonLaptop
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonPC.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonAccesory
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonPrice0_500k
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonPrice500k_1M
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonPrice1M_5M
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.radioButtonPrice5M_More
				.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));

		this.radioButtonComponent.addActionListener(JFrameMain.getInstance());
		this.radioButtonPhone.addActionListener(JFrameMain.getInstance());
		this.radioButtonLaptop.addActionListener(JFrameMain.getInstance());
		this.radioButtonPC.addActionListener(JFrameMain.getInstance());
		this.radioButtonAccesory.addActionListener(JFrameMain.getInstance());
		this.radioButtonPrice0_500k.addActionListener(JFrameMain.getInstance());
		this.radioButtonPrice500k_1M.addActionListener(JFrameMain.getInstance());
		this.radioButtonPrice1M_5M.addActionListener(JFrameMain.getInstance());
		this.radioButtonPrice5M_More.addActionListener(JFrameMain.getInstance());

		this.radioButtonComponent.setMnemonic(1);
		this.radioButtonPhone.setMnemonic(2);
		this.radioButtonLaptop.setMnemonic(3);
		this.radioButtonPC.setMnemonic(4);
		this.radioButtonAccesory.setMnemonic(5);

		this.radioButtonPrice0_500k.setMnemonic(1);
		this.radioButtonPrice500k_1M.setMnemonic(2);
		this.radioButtonPrice1M_5M.setMnemonic(3);
		this.radioButtonPrice5M_More.setMnemonic(4);

		this.buttonGroupType.add(radioButtonComponent);
		this.buttonGroupType.add(radioButtonPhone);
		this.buttonGroupType.add(radioButtonLaptop);
		this.buttonGroupType.add(radioButtonPC);
		this.buttonGroupType.add(radioButtonAccesory);
		this.buttonGroupPrice.add(radioButtonPrice0_500k);
		this.buttonGroupPrice.add(radioButtonPrice500k_1M);
		this.buttonGroupPrice.add(radioButtonPrice1M_5M);
		this.buttonGroupPrice.add(radioButtonPrice5M_More);

		this.radioButtonComponent.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonPhone.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonLaptop.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonPC.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonAccesory.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonPrice0_500k.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonPrice500k_1M.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonPrice1M_5M.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);
		this.radioButtonPrice5M_More.setActionCommand(Constants.COMMAND_RADIOBUTTON_FILTER);

		this.putOffFilters.setPreferredSize(new Dimension(100, 50));
		this.putOffFilters.setForeground(Color.WHITE);
		this.putOffFilters.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		this.putOffFilters.setBorder(new LineBorder(new Color(174, 11, 11), 3));
		this.putOffFilters.setBackground(new Color(201, 72, 72));
		this.putOffFilters.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.putOffFilters.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonGroupPrice.clearSelection();
				buttonGroupType.clearSelection();
				putOffFilters();
			}
		});
	}

	/**
	 * Metodo que filtra el inventario 
	 * @param text texto a filtrar
	 */
	private void filtre(String text) {
		jPanelProducts.filtre(text, getFiltersSelected());
	}

	/**
	 * Metodo que reinicia la busqueda
	 */
	public void restoreSearch() {
		this.jTextFieldSearch.setText("");
		filtre("");
	}

	/**
	 * Metodo que obtiene los filtros seleccionados
	 * @return filtros seleccionados 
	 */
	private int[] getFiltersSelected() {
		int[] filters = new int[] { -1, -1 };
		if (buttonGroupType.getSelection() != null) {
			filters[0] = buttonGroupType.getSelection().getMnemonic();
		}
		if (buttonGroupPrice.getSelection() != null) {
			filters[1] = buttonGroupPrice.getSelection().getMnemonic();
		}
		return filters;
	}

	/**
	 * Metodo que muestra o quita el menu de filtros
	 */
	public void showFilters() {
		if (jPopupMenu.isVisible()) {
			jPopupMenu.setVisible(false);
		} else {
			jPopupMenu.setVisible(true);
		}
	}

	/**
	 * Metodo que activa o no los filtros
	 * @param active true o false
	 */
	public void showFilters(boolean active) {
		jPopupMenu.setVisible(active);
	}

	/**
	 * Metodo que inicia las propiedades de los filtros
	 */
	private void initPropFilters() {
		jPopupMenu.setPreferredSize(
				new Dimension(JFrameMain.WIDTH_SIZE * 500 / 1920, JFrameMain.HEIGTH_SIZE * 210 / 1080));
		jPopupMenu.setLocation(JFrameMain.WIDTH_SIZE * 1250 / 1920, JFrameMain.HEIGTH_SIZE * 330 / 1080);
		jPopupMenu.setLayout(new GridBagLayout());
		jPopupMenu.setBorder(new LineBorder(new Color(48, 116, 180), 3));
		jPopupMenu.getComponent().setBackground(new Color(157, 195, 230));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = 1;
		gbc.weightx = 1;
		this.jPopupMenu.add(jLabelTittleType, gbc);
		gbc.gridx = 1;
		this.jPopupMenu.add(jLabelTittlePrice, gbc);
		gbc.gridy = 1;
		gbc.gridx = 0;
		this.jPopupMenu.add(radioButtonComponent, gbc);
		gbc.gridx = 1;
		this.jPopupMenu.add(radioButtonPrice0_500k, gbc);
		gbc.gridy = 2;
		gbc.gridx = 0;
		this.jPopupMenu.add(radioButtonPhone, gbc);
		gbc.gridx = 1;
		this.jPopupMenu.add(radioButtonPrice500k_1M, gbc);
		gbc.gridy = 3;
		gbc.gridx = 0;
		this.jPopupMenu.add(radioButtonLaptop, gbc);
		gbc.gridx = 1;
		this.jPopupMenu.add(radioButtonPrice1M_5M, gbc);
		gbc.gridy = 4;
		gbc.gridx = 0;
		this.jPopupMenu.add(radioButtonPC, gbc);
		gbc.gridx = 1;
		this.jPopupMenu.add(radioButtonPrice5M_More, gbc);
		gbc.gridy = 5;
		gbc.gridx = 0;
		this.jPopupMenu.add(radioButtonAccesory, gbc);
		gbc.gridy = 5;
		gbc.gridx = 1;
		gbc.insets = new Insets(0, 200 * JFrameMain.WIDTH_SIZE / 1920, 0, 0);
		this.jPopupMenu.add(putOffFilters, gbc);

	}

	/**
	 * Metodo que aplica los filtros
	 */
	public void applyFilters() {
		jPanelProducts.filtre(jTextFieldSearch.getText(), getFiltersSelected());
	}

	/**
	 * Metodo que quita los filtros
	 */
	public void putOffFilters() {
		jPanelProducts.putOffFilters();
	}

	/**
	 * Metodo que pone en cero las cantidades de los productos
	 */
	public void setCeroQuanty() {
		this.jPanelProducts.setCeroQuanty();
	}

	/**
	 * Metodo que obtiene la informacion de un producto por codigo
	 * @param code codigo del producto
	 * @return informacion del producto
	 */
	public Object[] getProductInfo(int code) {
		return jPanelProducts.getProductInfo(code);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(48, 116, 180));
		g2d.fillRect(JFrameMain.WIDTH_SIZE / 1920, 0, getWidth(), 75 * JFrameMain.HEIGTH_SIZE / 1080);
		super.paint(g);
	}

	/**
	 * Metodo que obtiene la cantidad maxima de un producto
	 * @param code codigo del producto
	 * @return cantidad maxima del producto
	 */
	public int getMaxQuantyOf(int code) {
		return jPanelProducts.getMaxQuantyOf(code);
	}

	public void actualizeInventory(ArrayList<Object[]> inventoryActualized) {
		jPanelProducts.setProducts(inventoryActualized);
	}

}
