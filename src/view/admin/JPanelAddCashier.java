package view.admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import controller.Controller;
import view.JFrameMain;
import view.utils.Constants;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelAddCashier extends JPanel {

	private GridBagConstraints gbc;
	private JLabel jlabelNameCashier;
	private JLabel jlabelLastNameCashier;
	private JLabel jlabelNumberIDCashier;
	private JLabel jlabelUserNameCashier;
	private JLabel jlabelPasswordCashier;
	private JLabel jlabelConfirmPasswordCashier;
	private JLabel jlabelEmailCashier;

	private JTextField jTextFieldNameCashier;
	private JTextField jTextFieldLastNameCashier;
	private JTextField jTextFieldNumberIDCashier;
	private JTextField jTextFieldUserNameCashier;
	private JPasswordField jTextFieldPasswordCashier;
	private JPasswordField jTextFieldConfirmPasswordCashier;
	private JTextField jTextFieldEmailCashier;

	private JButton buttonAdd;
	private JButton buttonBack;

	private JPanel jPanelColumn1;
	private JPanel jPanelColumn2;

	/**
	 * Constructor de JPanelAddCashier
	 */
	public JPanelAddCashier() {
		super(new GridLayout(1, 3));
		this.gbc = new GridBagConstraints();
		this.jPanelColumn1 = new JPanel(new GridBagLayout());
		this.jPanelColumn2 = new JPanel(new GridBagLayout());

		this.buttonAdd = new JButton("Agregar");
		this.buttonBack = new JButton("Atras");

		this.jlabelNameCashier = new JLabel("Nombre Cajero:");
		this.jlabelLastNameCashier = new JLabel("Apellido Producto:");
		this.jlabelNumberIDCashier = new JLabel("Numero de Documento:");
		this.jlabelUserNameCashier = new JLabel("Nombre de Usuario:");
		this.jlabelPasswordCashier = new JLabel("Contraseña de Usuario:");
		this.jlabelConfirmPasswordCashier = new JLabel("Confirmar Contraseña:");
		this.jlabelEmailCashier = new JLabel("Correo Electrónico:");

		this.jTextFieldNameCashier = new JTextField();
		this.jTextFieldLastNameCashier = new JTextField();
		this.jTextFieldNumberIDCashier = new JTextField();
		this.jTextFieldUserNameCashier = new JTextField();
		this.jTextFieldPasswordCashier = new JPasswordField();
		this.jTextFieldConfirmPasswordCashier = new JPasswordField();
		this.jTextFieldEmailCashier = new JTextField();
		init();
	}

	/**
	 * Metodo que inicializa las propiedades de este panel
	 */
	private void init() {
		this.setOpaque(false);
		initPropComponents();
		this.add(jPanelColumn1);
		jPanelColumn1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3, new Color(48, 116, 180, 50)));
		this.add(jPanelColumn2);
		gbc.fill = 1;
		initColumn1();
		initColumn2();
	}

	/**
	 * Metodo que inicializa las propiedades de la columna 2
	 */
	private void initColumn2() {
		gbc.insets = new Insets(10, 30, 0, 30);
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.jPanelColumn2.add(jlabelConfirmPasswordCashier, gbc);
		gbc.insets.bottom = 40;
		gbc.gridy = 1;
		this.jPanelColumn2.add(jTextFieldConfirmPasswordCashier, gbc);
		gbc.insets.bottom = 0;
		gbc.gridy = 2;
		this.jPanelColumn2.add(jlabelEmailCashier, gbc);
		gbc.insets.bottom = 40;
		gbc.gridy = 3;
		gbc.insets.bottom = 290;
		this.jPanelColumn2.add(jTextFieldEmailCashier, gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 4;
		gbc.insets.left = 50;
		gbc.insets.right = 50;
		gbc.insets.top = 80;
		gbc.insets.bottom = 30;
		this.jPanelColumn2.add(buttonAdd, gbc);
		gbc.gridx = 1;
		gbc.insets.left = 0;
		this.jPanelColumn2.add(buttonBack, gbc);
	}

	/**
	 * Metodo que inicializa las propiedades de la columna 1
	 */
	private void initColumn1() {
		gbc.insets = new Insets(10, 30, 0, 30);
		gbc.weightx = 1;
		this.jPanelColumn1.add(jlabelNameCashier, gbc);
		gbc.gridy = 1;
		gbc.insets.bottom = 40;
		this.jPanelColumn1.add(jTextFieldNameCashier, gbc);
		gbc.gridy = 2;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jlabelLastNameCashier, gbc);
		gbc.gridy = 3;
		gbc.insets.bottom = 40;
		this.jPanelColumn1.add(jTextFieldLastNameCashier, gbc);
		gbc.gridy = 4;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jlabelNumberIDCashier, gbc);
		gbc.gridy = 5;
		gbc.insets.bottom = 40;
		this.jPanelColumn1.add(jTextFieldNumberIDCashier, gbc);
		gbc.gridy = 6;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jlabelUserNameCashier, gbc);
		gbc.gridy = 7;
		gbc.insets.bottom = 40;
		this.jPanelColumn1.add(jTextFieldUserNameCashier, gbc);
		gbc.gridy = 8;
		gbc.insets.bottom = 0;
		this.jPanelColumn1.add(jlabelPasswordCashier, gbc);
		gbc.gridy = 9;
		gbc.insets.bottom = 40;
		this.jPanelColumn1.add(jTextFieldPasswordCashier, gbc);
	}

	/**
	 * Metodo que inicializa las propiedades de los componentes
	 */
	private void initPropComponents() {
		Font fontLabels = new Font("Arial Rounded MT Bold", Font.BOLD, 30 * JFrameMain.WIDTH_SIZE / 1920);
		Font fontTextField = new Font("Arial", Font.ITALIC, 25 * JFrameMain.WIDTH_SIZE / 1920);
		Color colorButton = new Color(48, 116, 180);
		Color colorFont = new Color(47, 85, 151);
		Border border = new LineBorder(Color.BLACK, 3);

		this.jlabelNameCashier.setFont(fontLabels);
		this.jlabelLastNameCashier.setFont(fontLabels);
		this.jlabelNumberIDCashier.setFont(fontLabels);
		this.jlabelUserNameCashier.setFont(fontLabels);
		this.jlabelPasswordCashier.setFont(fontLabels);
		this.jlabelConfirmPasswordCashier.setFont(fontLabels);
		this.jlabelEmailCashier.setFont(fontLabels);

		this.jTextFieldNameCashier.setFont(fontTextField);
		this.jTextFieldLastNameCashier.setFont(fontTextField);
		this.jTextFieldNumberIDCashier.setFont(fontTextField);
		this.jTextFieldUserNameCashier.setFont(fontTextField);
		this.jTextFieldPasswordCashier.setFont(fontTextField);
		this.jTextFieldConfirmPasswordCashier.setFont(fontTextField);
		this.jTextFieldEmailCashier.setFont(fontTextField);

		this.jlabelNameCashier.setForeground(colorFont);
		this.jlabelLastNameCashier.setForeground(colorFont);
		this.jlabelNumberIDCashier.setForeground(colorFont);
		this.jlabelUserNameCashier.setForeground(colorFont);
		this.jlabelPasswordCashier.setForeground(colorFont);
		this.jlabelConfirmPasswordCashier.setForeground(colorFont);
		this.jlabelEmailCashier.setForeground(colorFont);

		this.jTextFieldNameCashier.setForeground(Color.BLACK);
		this.jTextFieldLastNameCashier.setForeground(Color.BLACK);
		this.jTextFieldNumberIDCashier.setForeground(Color.BLACK);
		this.jTextFieldUserNameCashier.setForeground(Color.BLACK);
		this.jTextFieldPasswordCashier.setForeground(Color.BLACK);
		this.jTextFieldConfirmPasswordCashier.setForeground(Color.BLACK);
		this.jTextFieldEmailCashier.setForeground(Color.BLACK);

		this.jTextFieldNameCashier.setOpaque(false);
		this.jTextFieldLastNameCashier.setOpaque(false);
		this.jTextFieldNumberIDCashier.setOpaque(false);
		this.jTextFieldUserNameCashier.setOpaque(false);
		this.jTextFieldPasswordCashier.setOpaque(false);
		this.jTextFieldConfirmPasswordCashier.setOpaque(false);
		this.jTextFieldEmailCashier.setOpaque(false);

		this.jTextFieldNameCashier.setBorder(border);
		this.jTextFieldLastNameCashier.setBorder(border);
		this.jTextFieldNumberIDCashier.setBorder(border);
		this.jTextFieldUserNameCashier.setBorder(border);
		this.jTextFieldPasswordCashier.setBorder(border);
		this.jTextFieldConfirmPasswordCashier.setBorder(border);
		this.jTextFieldEmailCashier.setBorder(border);

		this.buttonAdd.setFont(fontTextField);
		this.buttonAdd.setRolloverEnabled(false);
		this.buttonAdd.setFocusPainted(false);
		this.buttonAdd.setBackground(colorButton);
		this.buttonAdd.setForeground(Color.WHITE);
		this.buttonAdd.setBorder(border);
		this.buttonAdd.addActionListener(Controller.getInstance());
		this.buttonAdd.setActionCommand(Constants.COMMAND_ADD_CASHIER);

		this.buttonBack.setFont(fontTextField);
		this.buttonBack.setRolloverEnabled(false);
		this.buttonBack.setFocusPainted(false);
		this.buttonBack.setBackground(colorButton);
		this.buttonBack.setForeground(Color.WHITE);
		this.buttonBack.setBorder(border);
		this.buttonBack.addActionListener(JFrameMain.getInstance());
		this.buttonBack.setActionCommand(Constants.COMMAND_BUTTON_BACK_ADDCASHIER);

		this.jPanelColumn1.setOpaque(false);
		this.jPanelColumn2.setOpaque(false);
	}

	/**
	 * Metodo que obtiene los datos del cajero
	 */
	public String[] getInfoNewCashier() {
		return new String[] { jTextFieldNameCashier.getText(), jTextFieldLastNameCashier.getText(),
				jTextFieldNumberIDCashier.getText(), jTextFieldUserNameCashier.getText(),
				new String(jTextFieldPasswordCashier.getPassword()),
				new String(jTextFieldConfirmPasswordCashier.getPassword()), jTextFieldEmailCashier.getText() };
	}

	/**
	 * Metodo que pone en blanco todos los campos de texto
	 */
	public void putInfoBlank() {
		jTextFieldNameCashier.setText("");
		jTextFieldLastNameCashier.setText("");
		jTextFieldNumberIDCashier.setText("");
		jTextFieldUserNameCashier.setText("");
		jTextFieldPasswordCashier.setText("");
		jTextFieldConfirmPasswordCashier.setText("");
		jTextFieldEmailCashier.setText("");
	}

	/**
	 * Metodo que resalta los campos de texto
	 * 
	 * @param index posicion del campo de texto
	 * @param color color 0 red, 1 black
	 */
	public void underline(int index, int color) {
		Border borderRed = new LineBorder(Color.RED, 3);
		Border borderBlack = new LineBorder(Color.BLACK, 3);
		switch (index) {
		case 0:
			if (color == 0) {
				jTextFieldNameCashier.setBorder(borderRed);
			} else {
				jTextFieldNameCashier.setBorder(borderBlack);
			}
			break;
		case 1:
			if (color == 0) {
				jTextFieldLastNameCashier.setBorder(borderRed);
			} else {
				jTextFieldLastNameCashier.setBorder(borderBlack);
			}
			break;
		case 2:
			if (color == 0) {
				jTextFieldNumberIDCashier.setBorder(borderRed);
			} else {
				jTextFieldNumberIDCashier.setBorder(borderBlack);
			}
			break;
		case 3:
			if (color == 0) {
				jTextFieldUserNameCashier.setBorder(borderRed);
			} else {
				jTextFieldUserNameCashier.setBorder(borderBlack);
			}
			break;
		case 4:
			if (color == 0) {
				jTextFieldPasswordCashier.setBorder(borderRed);
			} else {
				jTextFieldPasswordCashier.setBorder(borderBlack);
			}
			break;
		case 5:
			if (color == 0) {
				jTextFieldConfirmPasswordCashier.setBorder(borderRed);
			} else {
				jTextFieldConfirmPasswordCashier.setBorder(borderBlack);
			}
			break;
		case 6:
			if (color == 0) {
				jTextFieldEmailCashier.setBorder(borderRed);
			} else {
				jTextFieldEmailCashier.setBorder(borderBlack);
			}
			break;
		}
	}
}
