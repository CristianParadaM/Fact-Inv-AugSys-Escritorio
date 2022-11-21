package view.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controller.Controller;
import view.JFrameMain;
import view.utils.JTablePodium;
/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelContentAdmin extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridBagConstraints gbc;
	private JLabel jLabelTittle;
	private JLabel jLabelTittleReport;
	private JLabel jLabelTittleAddCashier;
	private JPanel jPanelContainerReport; // reporte
	private JPanelAddCashier jPanelContainerAddCashier;
	private JPanelOptionCashier jPanelOptionCashier;
	private JPanelOptionProduct jPanelOptionProduct;
	private JPanelOptionSalesHistory jPanelOptionSalesHistory;
	private JPanelAddProduct jPanelCointainerAddProduct;
	private JLabel jlabelTittleAddProduct;
	private IListener iListener;

	/**
	 * Constructor de JPanelContentAdmin
	 * @param quantyCashiers cantidad de cajeros registrados
	 * @param quantyProducts cantidad de productos registrados
	 */
	public JPanelContentAdmin(int quantyCashiers, int quantyProducts) {
		super(new GridBagLayout());
		this.gbc = new GridBagConstraints();
		this.jlabelTittleAddProduct= new JLabel("AÑADIR PRODUCTO", JLabel.CENTER);
		this.jPanelContainerReport = new JPanel(new GridLayout(1,3));
		this.jLabelTittle = new JLabel("CONTROL Y ESTADÍSTICAS DEL NEGOCIO", JLabel.CENTER);
		this.jLabelTittleAddCashier = new JLabel("AÑADIR CAJERO", JLabel.CENTER);
		this.jPanelContainerAddCashier = new JPanelAddCashier();
		this.jLabelTittleReport = new JLabel("", JLabel.CENTER);
		this.jPanelOptionCashier = new JPanelOptionCashier(quantyCashiers);
		this.jPanelOptionProduct = new JPanelOptionProduct(quantyProducts);
		this.jPanelOptionSalesHistory = new JPanelOptionSalesHistory();
		this.jPanelCointainerAddProduct = new JPanelAddProduct();
		this.iListener = Controller.getInstance();
		init();
	}

	/**
	 * Metodo que inicializa las propiedades de este panel
	 */
	private void init() {
		this.setOpaque(false);
		initPropLabels();
		switchSalesReport("12/02/2022", "15/02/2022",
		new Object[] {
					
			new Object[] { 
				 new int[] { 90, 90, 20, 100, 60 }, //angulos de la grafica
					
			new String[] { "Componentes 40%", "PC 10%", "Accesorios 20%", 
					"Laptop 30%", "Celulares 10%" }, 200 }, // nombres con porcentaje y total
			
			new Object[] { "Componentes", 42.23f },  // producto mas vendido y porcentaje de ventas
			
			new Object[] { "PC", 2.23f }, // producto menos vendido y porcentaje de ventas
					
			new Object[][] { // datos de la tabla de mejores cajeros
						{ 1, "Cristian David Parada Martinez", 30 },
						{ 2, "Daniel Felipe Suarez Bohorquez", 20 }, 
						{ 3, "Nicolas David Fajardo Acuña", 10 } 
						},
				
			new Object[][] { //datos de la tabla productos mas vendidos
							{ 1, "Xiaomi Poco X3 PRO", 310 }, 
							{ 2, "Mouse X Gaming pro max", 220 },
							{ 3, "HP Omen 16 GB", 130 } 
							},
			
			200000000000.0, // ventas totales en precio
			6000000000.0  // ganancia total obtenida en precio
					});
		
//		switchInitMenu();
	}

	/**
	 * Metodo que cambia la vista a reporte de ventas
	 * @param dateInitial fecha inicial 
	 * @param dateFinal fecha final
	 * @param info informacion del reporte
	 */
	public void switchSalesReport(String dateInitial, String dateFinal, Object[] info) {
		for (int i = 0; i < this.jPanelContainerReport.getComponentCount(); i++) {
			this.jPanelContainerReport.getComponent(i).setVisible(false);
		}
		initPropReport(dateInitial, dateFinal);
		createReport(info);
		removeAllComponents();
		jLabelTittle.setVisible(true);
		jPanelContainerReport.setVisible(true);
		gbc.fill = 1;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(2 * JFrameMain.HEIGTH_SIZE / 1080, 0, 0, 0);
		this.add(jLabelTittleReport, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 0, 0, 0);
		this.add(jPanelContainerReport, gbc);
	}

	// [0] Object[] dataPieChart(int[] angles, String[] legend, int totalBuys),
	// [1] Object[] dataTypeMostBuy(String name, float percent),
	// [2] Object[] dataTypeMenosBuy(String name, float percent),
	// [3] Object[][] dataTableCashiers(int position, String name, int sales),
	// [4] Object[][] dataTableProducts(int position, String name, int sales),
	// [5] double valor total ventas
	// [6] double valor ganancias
	
	/**
	 * Metodo que crea el reporte
	 * @param info informacion del reporte
	 */
	@SuppressWarnings("unchecked")
	private void createReport(Object[] info) {
		JPanel jPanelColumn1 = new JPanel(new GridBagLayout());
		JPanel jPanelColumn2 = new JPanel(new GridBagLayout());
		JPanel jPanelColumn3 = new JPanel(new GridBagLayout());
		
		jPanelColumn1.setOpaque(false);
		jPanelColumn2.setOpaque(false);
		jPanelColumn3.setOpaque(false);
		
		jPanelColumn1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(48, 116, 180, 50)));
		jPanelColumn2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(48, 116, 180, 50)));
		jPanelContainerReport.setOpaque(false);
		
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel jLabelTittleChart = new JLabel("Ventas totales", JLabel.CENTER);
		JLabel jLabelTittlePMore = new JLabel("Tipo de producto más vendido", JLabel.CENTER);
		JLabel jLabelTittlePLess = new JLabel("Tipo de producto menos vendido", JLabel.CENTER);
		JLabel jLabelTittleCashiers = new JLabel("Cajeros con mas ventas", JLabel.CENTER);
		JLabel jLabelTittleProducts = new JLabel("Productos con mas ventas", JLabel.CENTER);
		JLabel jLabelTittleGains = new JLabel("Ganancias obtenidas", JLabel.CENTER);
		
		JPanelPieChart chart = new JPanelPieChart(((int[]) ((Object[]) info[0])[0]),
				((String[]) ((Object[]) info[0])[1]), ((int) ((Object[]) info[0])[2]));

		JLabel jLabelImageProductM = new JLabel(
				new ImageIcon(new ImageIcon("res/p1.png").getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH)));
		JLabel jLabelImageProductL = new JLabel(
				new ImageIcon(new ImageIcon("res/p4.png").getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH)));
		JLabel jlabelTypeProductM = new JLabel(((String) ((Object[]) info[1])[0]), JLabel.CENTER);
		JLabel jlabelTypeProductL = new JLabel(((String) ((Object[]) info[2])[0]), JLabel.CENTER);
		JLabel jlabelTextProductM = new JLabel("<html><p align='justify'>Con un total del "+((float) ((Object[]) info[1])[1])+"% de las ventas "
				+ "totales con respecto a los demás productos</p><html>");
		JLabel jlabelTextProductL = new JLabel("<html><p align='justify'>Con un total del "+((float) ((Object[]) info[2])[1])+"% de las ventas "
				+ "totales con respecto a los demás productos</p><html>");
		JTablePodium jTablePodiumCashiers = new JTablePodium(((ArrayList<Object[]>)info[3]), 0);
		JTablePodium jTablePodiumProducts = new JTablePodium(((ArrayList<Object[]>)info[4]), 1);
		jTablePodiumProducts.setPreferredSize(new Dimension(0,250));
		JLabel jLabelTotalSales = new JLabel("  Ventas Totales ($):");
		JLabel jLabelTotalGains = new JLabel("  Ganancias ($):");
		DecimalFormat formato = new DecimalFormat("#,###.00");
		JLabel jLabelTotalSalesText = new JLabel(formato.format((double)info[5])+"   ", JLabel.RIGHT);
		JLabel jLabelTotalGainsText = new JLabel(formato.format((double)info[6])+"   ", JLabel.RIGHT);
		JButton buttonBack = new JButton("atras");
		buttonBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchInitMenu();
			}
		});
		JButton buttonPrint = new JButton("imprimir");

		buttonBack.setRolloverEnabled(false);
		buttonPrint.setRolloverEnabled(false);
		buttonBack.setFocusPainted(false);
		buttonPrint.setFocusPainted(false);
		buttonPrint.setRolloverEnabled(false);
		buttonBack.setBackground(new Color(157, 195, 230));
		buttonPrint.setBackground(new Color(157, 195, 230));
		
		buttonBack.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		buttonPrint.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTotalSalesText.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTotalGainsText.setFont(new Font("Arial", Font.ITALIC, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTotalSales.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTotalGains.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTextProductM.setFont(new Font("Arial", Font.PLAIN, 30 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTextProductL.setFont(new Font("Arial", Font.PLAIN, 30 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTypeProductM.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTypeProductL.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTittleChart.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTittlePMore.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTittlePLess.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTittleCashiers.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTittleProducts.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32 * JFrameMain.WIDTH_SIZE / 1920));
		jLabelTittleGains.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 32 * JFrameMain.WIDTH_SIZE / 1920));

		jLabelTotalSalesText.setForeground(Color.BLACK);
		jLabelTotalGainsText.setForeground(Color.BLACK);
		jLabelTotalSales.setForeground(Color.BLACK);
		jLabelTotalGains.setForeground(Color.BLACK);
		jlabelTextProductM.setForeground(Color.BLACK);
		jlabelTextProductL.setForeground(Color.BLACK);
		jlabelTypeProductM.setForeground(Color.BLACK);
		jlabelTypeProductL.setForeground(Color.BLACK);
		jLabelTittleChart.setForeground(Color.BLACK);
		jLabelTittlePMore.setForeground(Color.BLACK);
		jLabelTittlePLess.setForeground(Color.BLACK);
		jLabelTittleCashiers.setForeground(Color.BLACK);
		jLabelTittleProducts.setForeground(Color.BLACK);
		jLabelTittleGains.setForeground(Color.BLACK);

		gbc.fill = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.insets.left = 5;
		gbc.insets.right = 10;
		gbc.gridx = 0;
		gbc.gridy = 0;
		jPanelColumn1.add(jLabelTittleChart, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1;
		jPanelColumn1.add(chart, gbc);
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets.top = 5;
		gbc.insets.bottom = 20;
		jPanelColumn1.add(jLabelTittlePMore, gbc);
		gbc.insets.top = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets.bottom = 50;
		gbc.insets.right = 0;
		jPanelColumn1.add(jLabelImageProductM, gbc);
		gbc.insets.bottom = 0;
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets.left = 0;
		gbc.insets.right = 10;
		jPanelColumn1.add(jlabelTypeProductM, gbc);
		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets.bottom = 50;
		jPanelColumn1.add(jlabelTextProductM, gbc);
		// segunda columna
		gbc.fill = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		gbc.insets.left = 0;
		gbc.insets.right = 0;
		gbc.insets.bottom = 20;
		gbc.gridx = 0;
		gbc.gridy = 0;
		jPanelColumn2.add(jLabelTittlePLess, gbc);
		gbc.insets.top = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.bottom = 135;
		gbc.insets.right = 5;
		gbc.insets.left = 10;
		gbc.weightx = 0;
		jPanelColumn2.add(jLabelImageProductL, gbc);
		gbc.weightx = 1;
		gbc.insets.bottom = 0;
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets.left = 5;
		gbc.insets.right = 10;
		jPanelColumn2.add(jlabelTypeProductL, gbc);
		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets.bottom =135;
		jPanelColumn2.add(jlabelTextProductL, gbc);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets.bottom = 20;
		jPanelColumn2.add(jLabelTittleCashiers, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weightx = 1;
		gbc.weighty = 1;
		jPanelColumn2.add(jTablePodiumCashiers, gbc);
		// tercer columna
		gbc.insets = new Insets(0, 10, 20, 10);
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		jPanelColumn3.add(jLabelTittleProducts, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.bottom = 140;
		jPanelColumn3.add(jTablePodiumProducts, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets.bottom = 10;
		jPanelColumn3.add(jLabelTittleGains, gbc);
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets.bottom = 5;
		jPanelColumn3.add(jLabelTotalSales, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		jPanelColumn3.add(jLabelTotalSalesText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets.bottom = 20;
		jPanelColumn3.add(jLabelTotalGains, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		jPanelColumn3.add(jLabelTotalGainsText, gbc);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets.bottom = 190;
		jPanelColumn3.add(buttonBack, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		jPanelColumn3.add(buttonPrint, gbc);
		
		this.jPanelContainerReport.add(jPanelColumn1);
		this.jPanelContainerReport.add(jPanelColumn2);
		this.jPanelContainerReport.add(jPanelColumn3);
	}

	/**
	 * Metodo que inicializa las propiedades de los labels
	 * @param dateInitial fecha inicial
	 * @param dateFinal fecha final
	 */
	private void initPropReport(String dateInitial, String dateFinal) {
		this.jLabelTittleReport = new JLabel("Reporte Estadistico: " + dateInitial + " - " + dateFinal, JLabel.CENTER);
		this.jLabelTittleReport
				.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelTittleReport.setForeground(Color.WHITE);
	}

	/**
	 * metodo que cambia la vista a menu principal de admin
	 */
	public void switchInitMenu() {
		removeAllComponents();
		jLabelTittle.setVisible(true);
		jPanelOptionCashier.setVisible(true);
		jPanelOptionProduct.setVisible(true);
		jPanelOptionSalesHistory.setVisible(true);
		gbc = new GridBagConstraints();
		gbc.fill = 1;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(2 * JFrameMain.HEIGTH_SIZE / 1080, 0, 0, 0);
		this.add(jLabelTittle, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(30 * JFrameMain.HEIGTH_SIZE / 1080, 30 * JFrameMain.WIDTH_SIZE / 1920,
				10 * JFrameMain.HEIGTH_SIZE / 1080, 10 * JFrameMain.WIDTH_SIZE / 1920);
		this.add(jPanelOptionCashier, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets.top = 10 * JFrameMain.HEIGTH_SIZE / 1080;
		gbc.insets.bottom = 30 * JFrameMain.HEIGTH_SIZE / 1080;
		this.add(jPanelOptionProduct, gbc);
		gbc.insets = new Insets(30 * JFrameMain.HEIGTH_SIZE / 1080, 10 * JFrameMain.WIDTH_SIZE / 1920,
				30 * JFrameMain.HEIGTH_SIZE / 1080, 30 * JFrameMain.WIDTH_SIZE / 1920);
		gbc.gridy = 1;
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.gridheight = 2;
		this.add(jPanelOptionSalesHistory, gbc);
	}

	/**
	 * Metodo que remueve todos los componentes de este panel
	 */
	private void removeAllComponents() {
		for (int i = 0; i < this.getComponentCount(); i++) {
			this.getComponent(i).setVisible(false);
		}
	}

	/**
	 * Metodo que inicializa las propiedades de los labels
	 */
	private void initPropLabels() {
		this.jLabelTittle.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelTittle.setForeground(Color.WHITE);

		jPanelOptionSalesHistory.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				jPanelOptionSalesHistory
						.setBorder(new LineBorder(new Color(255, 212, 75), 5 * JFrameMain.WIDTH_SIZE / 1920));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPanelOptionSalesHistory
						.setBorder(new LineBorder(new Color(127, 96, 0), 5 * JFrameMain.WIDTH_SIZE / 1920));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iListener.iEvent(jPanelOptionSalesHistory);
			}
		});

		jPanelOptionCashier.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				jPanelOptionCashier
						.setBorder(new LineBorder(new Color(185, 92, 204), 5 * JFrameMain.WIDTH_SIZE / 1920));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPanelOptionCashier
						.setBorder(new LineBorder(new Color(132, 47, 149), 5 * JFrameMain.WIDTH_SIZE / 1920));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iListener.iEvent(jPanelOptionCashier);
			}
		});

		jPanelOptionProduct.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				jPanelOptionProduct
						.setBorder(new LineBorder(new Color(121, 154, 213), 5 * JFrameMain.WIDTH_SIZE / 1920));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				jPanelOptionProduct
						.setBorder(new LineBorder(new Color(68, 114, 196), 5 * JFrameMain.WIDTH_SIZE / 1920));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iListener.iEvent(jPanelOptionProduct);
			}
		});
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		g.setColor(new Color(48, 116, 180));
		g.fillRect(0, 0, getWidth(), 70 * JFrameMain.HEIGTH_SIZE / 1080);
		super.paint(graphics);
	}

	/**
	 * Metodo que desactiva los calendarios
	 */
	public void desactiveCalendars() {
		jPanelOptionSalesHistory.desactiveCalendars();
	}

	/**
	 * Metodo que cambia la vista a añadir cajero
	 */
	public void switchAddCashier() {
		removeAllComponents();
		this.jLabelTittleAddCashier.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelTittleAddCashier.setForeground(Color.WHITE);
		this.jPanelContainerAddCashier.setOpaque(false);
		this.jLabelTittleAddCashier.setVisible(true);
		this.jPanelContainerAddCashier.setVisible(true);
		gbc.fill = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		gbc.weightx = 1;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(jLabelTittleAddCashier, gbc);
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(jPanelContainerAddCashier, gbc);
	}

	/**
	 * Metodo que cambia la vista a añadir producto
	 */
	public void switchAddProduct() {
		removeAllComponents();
		jlabelTittleAddProduct.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50 * JFrameMain.WIDTH_SIZE / 1920));
		jlabelTittleAddProduct.setForeground(Color.WHITE);
		jlabelTittleAddProduct.setVisible(true);
		jPanelCointainerAddProduct.setOpaque(false);
		jPanelCointainerAddProduct.setVisible(true);
		gbc.fill = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weighty = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		this.add(jlabelTittleAddProduct, gbc);
		gbc.weighty = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		this.add(jPanelCointainerAddProduct, gbc);
		
	}

	/**
	 * Metodo que obtiene la informacion del cajero a agregar
	 * @return informacion del cajero
	 */
	public String[] getNewCashierInfo() {
		return jPanelContainerAddCashier.getInfoNewCashier();
	}
	
	/**
	 * Metodo que resalta los campos de texto de agregar cajero
	 * @param index posicion
	 * @param color color 
	 */
	public void underlineAddCashierTo(int index, int color) {
		jPanelContainerAddCashier.underline(index, color);
	}

	/**
	 * Metodo que cambia la vista del administrador
	 */
	public void switchPrincipalMenuAdmin() {
		switchInitMenu();
	}

	
	/**
	 * Metodo que pone en blanco las cajas de texto del formulario addCashier
	 */
	public void putBlankAddCashierText() {
		jPanelContainerAddCashier.putInfoBlank();
	}

	/**
	 * Metodo que obtiene la informacion
	 * @return informacion del nuevo producto
	 */
	public String[] getNewProductInfo() {
		return jPanelCointainerAddProduct.getInfoNewProduct();
	}

	/**
	 * Metodo que resalta el campo de texto que se pida
	 * @param index posicion del campo de texto
	 * @param color color de resaltado
	 */
	public void underLineAddProductTo(int index, int color) {
		jPanelCointainerAddProduct.underlineTo(index, color);
	}

	/**
	 * Metodo que pone en blanco las cajas de texto de agregar producto
	 */
	public void putBlankAddProductText() {
		jPanelCointainerAddProduct.putBlankAddProductText();
	}
}
