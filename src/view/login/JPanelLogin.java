package view.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.Controller;
import model.TextPrompt;
import view.JFrameMain;
import view.admin.IListener;
import view.utils.Constants;
import view.utils.JButtonAS;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelLogin extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imageBackground;
	private ImageIcon imageLogo;
	private JPanel jpanelInfoLogin;
	private JButtonAS buttonExit;
	private JButtonAS buttonSend;
	private JButtonAS buttonMinimize;
	private JButtonAS buttonForgotPass;
	private GridBagConstraints gbc;
	private JLabel jlabelTittleLogin;
	private JLabel jlabelUsername;
	private JLabel jlabelPassword;
	private JTextField jTextFieldUsername;
	private JPasswordField jTextFieldPassword;
	// --
	private JPanel jpanelForgotPass;
	private JLabel jlabelTittleForgotPass;
	private JLabel jlabelUsernameDigit;
	private JTextField jTextFieldUsernameI;
	private JLabel jlabelEmailText;
	private JLabel jlabelEmail;
	private JTextField jTextFieldCode;
	private JLabel jlabelTime;
	private JButton jbuttonBackFP;
	private JButton jbuttonBackVerifyID;
	private JButton jbuttonBackVerifyCode;

	private JPanel jpanelChangePass;
	private JLabel jlabelUser;
	private JTextField jTextFieldUser;
	private JLabel jlabelNewPass;
	private JTextField jTextFieldNewPass;
	private JLabel jlabelConfirmNewPass;
	private JTextField jTextFieldConfirmNewPass;
	private JButton jbuttonChange;
	private ScheduledExecutorService scheduler;
	private boolean isExecuteTimer;
	private IListener iListener;

	/**
	 * Constructor de JPanelLogin
	 */
	public JPanelLogin() {
		super();
		this.jlabelTittleForgotPass = new JLabel("OLVID� MI CONTRASE�A", JLabel.CENTER);
		this.jlabelUsernameDigit = new JLabel("Nombre de usuario:");
		this.jlabelEmailText = new JLabel("Digite el c�digo enviado a su correo electr�nico:");
		this.jlabelTime = new JLabel("Tiempo restante: ");
		this.jlabelEmail = new JLabel("cristi********@upt*******");
		this.jTextFieldUsernameI = new JTextField();
		this.jTextFieldCode = new JTextField();
		this.jbuttonBackFP = new JButton(
				new ImageIcon(new ImageIcon("res/back.png").getImage().getScaledInstance(100 * JFrameMain.WIDTH_SIZE / 1300, 50 * JFrameMain.HEIGTH_SIZE / 800, Image.SCALE_SMOOTH)));
		this.jbuttonBackVerifyCode = new JButton("  verificar  ");
		this.jbuttonBackVerifyID = new JButton("  verificar  ");

		this.jpanelChangePass = new JPanel(new GridBagLayout());
		this.jlabelUser = new JLabel("Usuario");
		this.jlabelNewPass = new JLabel("Digite una nueva contrase�a:");
		this.jlabelConfirmNewPass = new JLabel("Confirme su nueva contrase�a:");
		this.jTextFieldUser = new JTextField();
		this.jTextFieldNewPass = new JTextField();
		this.jTextFieldConfirmNewPass = new JTextField();
		this.jbuttonChange = new JButton("Cambiar");

		this.jpanelChangePass = new JPanel(new GridBagLayout());
		this.jpanelForgotPass = new JPanel(new GridBagLayout());
		this.imageBackground = new ImageIcon(Constants.IMAGE_BACKGROUND_LOGIN);
		this.imageLogo = new ImageIcon(Constants.IMAGE_LOGO);
		this.buttonExit = new JButtonAS("X", Color.BLACK, 2, new Dimension(100 * JFrameMain.WIDTH_SIZE / 1300, 50* JFrameMain.HEIGTH_SIZE / 800 ));
		this.buttonMinimize = new JButtonAS("-", Color.BLACK, 2, new Dimension(100 * JFrameMain.WIDTH_SIZE / 1300, 50* JFrameMain.HEIGTH_SIZE / 800));
		this.jpanelInfoLogin = new JPanel(new GridBagLayout());
		this.jlabelTittleLogin = new JLabel(Constants.INICIAR_SESION, JLabel.CENTER);
		this.jlabelUsername = new JLabel(Constants.NOMBRE_DE_USUARIO);
		this.jlabelPassword = new JLabel(Constants.CONTRASEÑA);
		this.jTextFieldUsername = new JTextField();
		this.jTextFieldPassword = new JPasswordField();
		this.buttonSend = new JButtonAS(new ImageIcon(Constants.IMAGE_BUTTON_SEND), Color.BLACK, 5*JFrameMain.WIDTH_SIZE/1300,
				new Dimension(80* JFrameMain.WIDTH_SIZE / 1300, 80* JFrameMain.HEIGTH_SIZE / 800));
		this.buttonForgotPass = new JButtonAS(Constants.OLVIDÉ_MI_CONTRASEÑA, Color.BLACK, 0, new Dimension(100* JFrameMain.WIDTH_SIZE / 1300, 50* JFrameMain.HEIGTH_SIZE / 800));
		this.gbc = new GridBagConstraints();
		this.iListener = Controller.getInstance();
		init();
	}

	/**
	 * Metodo que inicia este panel
	 */
	private void init() {
		this.setOpaque(false);
		this.setLayout(null);
		initPropButtons();
		initPropPanelInfo();
		initPropPanelChangePass();
	}

	/**
	 * Metodo que inicia las propiedades de cambio de contrase�a
	 */
	@SuppressWarnings({ "unchecked" })
	private void initPropPanelChangePass() {
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel title = new JLabel(jlabelTittleForgotPass.getText(), JLabel.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40 * JFrameMain.WIDTH_SIZE / 1300));
		Font font = title.getFont();
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		title.setFont(font.deriveFont(attributes));
		this.jpanelChangePass.setOpaque(false);
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.insets = new Insets(30, 30, 50, 30);
		this.jpanelChangePass.add(title, gbc);
		gbc.gridy=1;
		gbc.insets.bottom=0;
		this.jpanelChangePass.add(jlabelUser, gbc);
		gbc.gridy=2;
		gbc.insets.top=0;
		gbc.insets.bottom=40;
		this.jpanelChangePass.add(jTextFieldUser, gbc);
		gbc.gridy=3;
		gbc.insets.bottom=0;
		this.jpanelChangePass.add(jlabelNewPass, gbc);
		gbc.gridy=4;
		gbc.insets.bottom=40;
		this.jpanelChangePass.add(jTextFieldNewPass, gbc);
		gbc.gridy=5;
		gbc.insets.bottom=0;
		this.jpanelChangePass.add(jlabelConfirmNewPass, gbc);
		gbc.gridy=6;
		gbc.insets.bottom=40;
		this.jpanelChangePass.add(jTextFieldConfirmNewPass, gbc);
		gbc.gridy=7;
		gbc.insets.top=100;
		gbc.insets.bottom=200;
		this.jpanelChangePass.add(jbuttonChange, gbc);

	}

	/**
	 * Metodo que cambia a la vista de olvido la contrase�a
	 */
	private void switchPreForgotPanel() {
		removeAllComponents();
		GridBagConstraints gbc = new GridBagConstraints();
		this.jpanelForgotPass.setOpaque(false);
		this.jpanelInfoLogin.setVisible(false);
		this.buttonSend.setVisible(false);
		this.jpanelForgotPass.setVisible(true);
		jlabelTittleForgotPass.setVisible(true);
		jlabelUsernameDigit.setVisible(true);
		jTextFieldUsernameI.setVisible(true);
		jbuttonBackFP.setVisible(true);
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.insets.top = 30* JFrameMain.HEIGTH_SIZE/800;
		gbc.insets.left = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.right = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.bottom = 300*JFrameMain.HEIGTH_SIZE/1300;
		this.jpanelForgotPass.add(jlabelTittleForgotPass, gbc);
		gbc.insets.top = 0;
		gbc.insets.bottom = 0;
		gbc.gridy = 1;
		this.jpanelForgotPass.add(jlabelUsernameDigit, gbc);
		gbc.gridy = 2;
		gbc.insets.bottom = 30* JFrameMain.HEIGTH_SIZE/800;
		this.jpanelForgotPass.add(jTextFieldUsernameI, gbc);
		gbc.insets.bottom = 400* JFrameMain.HEIGTH_SIZE/800;
		gbc.gridy = 3;
		gbc.insets.left = 250*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.right = 250*JFrameMain.WIDTH_SIZE/1300;
		this.jpanelForgotPass.add(jbuttonBackFP, gbc);
		this.add(jpanelForgotPass).setBounds(0,0,JFrameMain.WIDTH_SIZE/2, JFrameMain.HEIGTH_SIZE);
	}

	/**
	 * Metodo que cambia a la vista de cambiar contrase�a
	 */
	private void switchForgotCompleted() {
		removeAllComponents();
		GridBagConstraints gbc = new GridBagConstraints();
		jlabelTittleForgotPass.setVisible(true);
		jlabelUsernameDigit.setVisible(true);
		jTextFieldUsernameI.setVisible(true);
		jbuttonBackFP.setVisible(true);
		jlabelEmail.setVisible(true);
		jlabelEmailText.setVisible(true);
		jTextFieldCode.setVisible(true);
		jlabelTime.setVisible(true);
		this.jpanelForgotPass.setOpaque(false);
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.insets.top = 30* JFrameMain.HEIGTH_SIZE/800;
		gbc.insets.left = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.right = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.bottom = 100;
		this.jpanelForgotPass.add(jlabelTittleForgotPass, gbc);
		gbc.insets.top = 0;
		gbc.insets.bottom = 0;
		gbc.gridy = 1;
		this.jpanelForgotPass.add(jlabelUsernameDigit, gbc);
		gbc.gridy = 2;
		gbc.insets.bottom = 150* JFrameMain.HEIGTH_SIZE/800;
		this.jpanelForgotPass.add(jTextFieldUsernameI, gbc);
		gbc.weightx = 1;
		gbc.insets.bottom = 0;
		gbc.gridy = 3;
		gbc.insets.left = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.right = 40*JFrameMain.WIDTH_SIZE/1300;
		this.jpanelForgotPass.add(jlabelEmailText, gbc);
		gbc.insets.bottom = 5* JFrameMain.HEIGTH_SIZE/800;
		gbc.gridy = 4;
		this.jpanelForgotPass.add(jlabelEmail, gbc);
		gbc.gridy = 5;
		this.jpanelForgotPass.add(jTextFieldCode, gbc);
		gbc.gridy = 6;
		gbc.insets.bottom = 120* JFrameMain.HEIGTH_SIZE/800;
		this.jpanelForgotPass.add(jlabelTime, gbc);
		gbc.gridy = 7;
		gbc.insets.left = 250*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.right = 250*JFrameMain.WIDTH_SIZE/1300;
		this.jpanelForgotPass.add(jbuttonBackFP, gbc);
		initTimer();
	}

	/**
	 * Metodo que remueve todos los componentes de olvido la contrase�a
	 */
	private void removeAllComponents() {
		for (int i = 0; i < jpanelForgotPass.getComponentCount(); i++) {
			jpanelForgotPass.getComponent(i).setVisible(false);
		}
	}

	/**
	 * Metodo que inicia el contador de 5 min para el codigo del correo
	 */
	private void initTimer() {
		if (!isExecuteTimer) {
			scheduler = Executors.newScheduledThreadPool(1);
			Runnable runnable = new Runnable() {
				int minutes = 4;
				int seconds = 59;
				
				public void run() {
					jlabelTime.setText(
							"Tiempo restante: " + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
					seconds--;
					if (seconds < 0) {
						seconds = 59;
						minutes--;
						if (minutes < 0) {
							scheduler.shutdown();
							isExecuteTimer = false;
							iListener.iEvent(Constants.COMMAND_TIME_OUT);
						}
					}
				}
			};
			scheduler.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);
		}
	}

	private void initPropPanelInfo() {
		initPropLabels();
		initPropTextFields();
		this.jpanelInfoLogin.setOpaque(false);
		this.add(jpanelInfoLogin).setBounds(0, 0, JFrameMain.WIDTH_SIZE / 2, JFrameMain.HEIGTH_SIZE);
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.insets.bottom = 40* JFrameMain.HEIGTH_SIZE/800;
		this.jpanelInfoLogin.add(jlabelTittleLogin, gbc);
		gbc.insets.left = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.right = 40*JFrameMain.WIDTH_SIZE/1300;
		gbc.insets.bottom = 10* JFrameMain.HEIGTH_SIZE/800;
		gbc.gridy = 1;
		this.jpanelInfoLogin.add(jlabelUsername, gbc);
		gbc.insets.bottom = 40* JFrameMain.HEIGTH_SIZE/800;
		gbc.gridy = 2;
		this.jpanelInfoLogin.add(jTextFieldUsername, gbc);
		gbc.insets.bottom = 10* JFrameMain.HEIGTH_SIZE/800;
		gbc.gridy = 3;
		this.jpanelInfoLogin.add(jlabelPassword, gbc);
		gbc.gridy = 4;
		gbc.insets.bottom = 190* JFrameMain.HEIGTH_SIZE/800;
		this.jpanelInfoLogin.add(jTextFieldPassword, gbc);
		gbc.gridy = 5;
		this.jpanelInfoLogin.add(buttonForgotPass, gbc);
		gbc.insets.bottom = 0;
		gbc.gridy = 6;
		this.jpanelInfoLogin.add(Box.createRigidArea(new Dimension(20* JFrameMain.WIDTH_SIZE / 1300, 60* JFrameMain.HEIGTH_SIZE / 800)), gbc);
	}

	private void initPropTextFields() {
		this.jTextFieldUsername.setForeground(Color.WHITE);
		this.jTextFieldUsername.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));
		this.jTextFieldUsername.setBorder(new LineBorder(Color.BLACK, 5*JFrameMain.WIDTH_SIZE/1300));
		this.jTextFieldUsername.setOpaque(false);
		this.jTextFieldUsername.setPreferredSize(new Dimension(100* JFrameMain.WIDTH_SIZE / 1300, 40* JFrameMain.HEIGTH_SIZE / 800));
		TextPrompt textPrompt = new TextPrompt(Constants.USERNAME_TEXTPROMPT, jTextFieldUsername);
		textPrompt.changeAlpha(0.35f);
		textPrompt.changeStyle(Font.ITALIC);
		this.jTextFieldPassword.setForeground(Color.WHITE);
		this.jTextFieldPassword.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));
		this.jTextFieldPassword.setBorder(new LineBorder(Color.BLACK, 5*JFrameMain.WIDTH_SIZE/1300));
		this.jTextFieldPassword.setOpaque(false);
		this.jTextFieldPassword.setPreferredSize(new Dimension(100* JFrameMain.WIDTH_SIZE / 1300, 40* JFrameMain.HEIGTH_SIZE / 800));
		TextPrompt textPrompt1 = new TextPrompt(Constants.PASSWORD_TEXTPROMPT, jTextFieldPassword);
		textPrompt1.changeAlpha(0.35f);
		textPrompt1.changeStyle(Font.ITALIC);

		this.jTextFieldUsernameI.setForeground(Color.WHITE);
		this.jTextFieldCode.setForeground(Color.WHITE);
		this.jTextFieldUser.setForeground(Color.WHITE);
		this.jTextFieldNewPass.setForeground(Color.WHITE);
		this.jTextFieldConfirmNewPass.setForeground(Color.WHITE);

		this.jTextFieldUsernameI.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));
		this.jTextFieldCode.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));
		this.jTextFieldUser.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));
		this.jTextFieldNewPass.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));
		this.jTextFieldConfirmNewPass.setFont(new Font("Arial", Font.ITALIC, 25* JFrameMain.WIDTH_SIZE / 1300));

		this.jTextFieldUsernameI.setBorder(new LineBorder(Color.BLACK, 5));
		this.jTextFieldCode.setBorder(new LineBorder(Color.BLACK, 5));
		this.jTextFieldUser.setBorder(new LineBorder(Color.BLACK, 5));
		this.jTextFieldNewPass.setBorder(new LineBorder(Color.BLACK, 5));
		this.jTextFieldConfirmNewPass.setBorder(new LineBorder(Color.BLACK, 5));

		this.jTextFieldUsernameI.setOpaque(false);
		this.jTextFieldCode.setOpaque(false);
		this.jTextFieldUser.setOpaque(false);
		this.jTextFieldNewPass.setOpaque(false);
		this.jTextFieldConfirmNewPass.setOpaque(false);
		this.jTextFieldUsernameI.setLayout(new BorderLayout());
		TextPrompt textPrompt2 = new TextPrompt("Digite su nombre de usuario", jTextFieldUsernameI);
		textPrompt2.changeAlpha(0.35f);
		textPrompt2.changeStyle(Font.ITALIC);
		this.jTextFieldUsernameI.add(jbuttonBackVerifyID, BorderLayout.EAST);

		this.jTextFieldCode.setLayout(new BorderLayout());
		TextPrompt textPrompt3 = new TextPrompt("Digite el c�digo de 9 caracteres", jTextFieldCode);
		textPrompt3.changeAlpha(0.35f);
		textPrompt3.changeStyle(Font.ITALIC);
		this.jTextFieldCode.add(jbuttonBackVerifyCode, BorderLayout.EAST);

		TextPrompt textPrompt5 = new TextPrompt("Digite una contrase�a (M�x. 12 caracteres)", jTextFieldNewPass);
		textPrompt5.changeAlpha(0.35f);
		textPrompt5.changeStyle(Font.ITALIC);

		TextPrompt textPrompt6 = new TextPrompt("Digite una contrase�a (M�x. 12 caracteres)", jTextFieldConfirmNewPass);
		textPrompt6.changeAlpha(0.35f);
		textPrompt6.changeStyle(Font.ITALIC);

	}

	@SuppressWarnings("unchecked")
	private void initPropLabels() {
		this.jlabelTittleLogin.setForeground(Color.WHITE);
		this.jlabelTittleLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 60 * JFrameMain.WIDTH_SIZE / 1300));
		Font font = jlabelTittleLogin.getFont();
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		jlabelTittleLogin.setFont(font.deriveFont(attributes));
		this.jlabelUsername.setForeground(Color.WHITE);
		this.jlabelUsername.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelPassword.setForeground(Color.WHITE);
		this.jlabelPassword.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30* JFrameMain.WIDTH_SIZE / 1300));

		this.jlabelTittleForgotPass.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelTittleForgotPass.setForeground(Color.WHITE);
		Font font2 = jlabelTittleForgotPass.getFont();
		@SuppressWarnings("rawtypes")
		Map attributes2 = font2.getAttributes();
		attributes2.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		jlabelTittleForgotPass.setFont(font2.deriveFont(attributes2));
		this.jlabelUsernameDigit.setForeground(Color.WHITE);
		this.jlabelEmailText.setForeground(Color.WHITE);
		this.jlabelEmail.setForeground(Color.CYAN);
		this.jlabelTime.setForeground(Color.WHITE);
		this.jlabelUser.setForeground(Color.WHITE);
		this.jlabelNewPass.setForeground(Color.WHITE);
		this.jlabelConfirmNewPass.setForeground(Color.WHITE);

		this.jlabelUsernameDigit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelEmailText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelEmail.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelTime.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelUser.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelNewPass.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jlabelConfirmNewPass.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
	}

	@SuppressWarnings("unchecked")
	private void initPropButtons() {
		this.buttonExit.setForeground(Color.WHITE);
		this.buttonExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.buttonExit.setBackground(new Color(80, 80, 80, 200));
		this.buttonExit.addActionListener(Controller.getInstance());
		this.buttonExit.setActionCommand(Constants.COMMAND_BUTTON_EXIT);

		this.buttonMinimize.setForeground(Color.WHITE);
		this.buttonMinimize.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.buttonMinimize.setBackground(new Color(80, 80, 80, 200));
		this.buttonMinimize.addActionListener(JFrameMain.getInstance());
		this.buttonMinimize.setActionCommand(Constants.COMMAND_MINIMIZE);
		this.add(buttonMinimize).setBounds(JFrameMain.WIDTH_SIZE - buttonMinimize.getWidth() * 2, 0,
				buttonMinimize.getWidth(), buttonMinimize.getHeight());
		this.add(buttonExit).setBounds(JFrameMain.WIDTH_SIZE - buttonExit.getWidth(), 0, buttonExit.getWidth(),
				buttonExit.getHeight());

		this.buttonSend.setAnimated(true);
		this.buttonSend.addActionListener(Controller.getInstance());
		this.buttonSend.setActionCommand(Constants.COMMAND_LOGIN);
		this.buttonSend.setAnimatedSize(new Dimension(buttonSend.getWidth() + 10, buttonSend.getHeight() + 10));
		this.buttonSend.setBackground(new Color(80, 80, 80, 200));
		this.add(buttonSend).setBounds(JFrameMain.WIDTH_SIZE / 4 - (40*JFrameMain.WIDTH_SIZE/1300), JFrameMain.HEIGTH_SIZE / 2,
				buttonSend.getWidth(), buttonSend.getHeight());

		this.buttonForgotPass.setContentAreaFilled(false);
		this.buttonForgotPass.setForeground(Color.WHITE);
		this.buttonForgotPass.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 20* JFrameMain.WIDTH_SIZE / 1300));
		Font font = buttonForgotPass.getFont();
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		buttonForgotPass.setFont(font.deriveFont(attributes));
		this.buttonForgotPass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchPreForgotPanel();
			}
		});

		this.jbuttonBackVerifyID.setRolloverEnabled(false);
		this.jbuttonBackVerifyCode.setRolloverEnabled(false);
		this.jbuttonBackFP.setRolloverEnabled(false);
		this.jbuttonChange.setRolloverEnabled(false);

		this.jbuttonBackVerifyID.setFocusable(false);
		this.jbuttonBackVerifyCode.setFocusable(false);
		this.jbuttonBackFP.setFocusable(false);
		this.jbuttonChange.setFocusable(false);

		this.jbuttonBackVerifyID.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jbuttonBackVerifyCode.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jbuttonBackFP.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.jbuttonChange.setCursor(new Cursor(Cursor.HAND_CURSOR));

		this.jbuttonBackVerifyID.setContentAreaFilled(false);
		this.jbuttonBackVerifyCode.setContentAreaFilled(false);
		this.jbuttonBackFP.setContentAreaFilled(false);
		this.jbuttonChange.setContentAreaFilled(false);

		this.jbuttonBackVerifyID.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
		this.jbuttonBackVerifyCode.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0, Color.BLACK));
		this.jbuttonBackFP.setBorder(new LineBorder(Color.BLACK, 3));
		this.jbuttonChange.setBorder(new LineBorder(Color.BLACK, 3));

		this.jbuttonBackVerifyID.setForeground(Color.WHITE);
		this.jbuttonBackVerifyCode.setForeground(Color.WHITE);
		this.jbuttonChange.setForeground(Color.WHITE);

		this.jbuttonBackVerifyID.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jbuttonBackVerifyCode.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE / 1300));
		this.jbuttonChange.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 20* JFrameMain.WIDTH_SIZE / 1300));

		this.jbuttonBackVerifyID.addActionListener(Controller.getInstance());
		this.jbuttonBackVerifyID.setActionCommand(Constants.COMMAND_BUTTON_VERIFY_ID);
		this.jbuttonBackVerifyCode.addActionListener(Controller.getInstance());
		this.jbuttonBackVerifyCode.setActionCommand(Constants.COMMAND_BUTTON_VERIFY_CODE);
		this.jbuttonChange.addActionListener(Controller.getInstance());
		this.jbuttonChange.setActionCommand(Constants.COMMAND_BUTTON_CHANGE_PASS);
		this.jbuttonBackFP.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switchJPanelLogin();
				if (scheduler!= null) {
					scheduler.shutdown();
				}
				jbuttonBackVerifyID.setEnabled(true);
				isExecuteTimer = false;
			}

		});
	}

	public String getIdJtextField() {
		return jTextFieldUsernameI.getText();
	}

	public void addInfoCode() {
		switchForgotCompleted();
		jpanelForgotPass.setVisible(false);
		jpanelForgotPass.setVisible(true);
		this.jbuttonBackVerifyID.setEnabled(false);
	}

	private void switchJPanelLogin() {
		this.jpanelForgotPass.setVisible(false);
		this.jpanelChangePass.setVisible(false);
		this.jpanelInfoLogin.setVisible(true);
		this.buttonSend.setVisible(true);
		this.add(jpanelInfoLogin).setBounds(0, 0, getWidth() / 2, getHeight());
	}

	public void switchChangePassword() {
		this.jpanelForgotPass.setVisible(false);
		this.jpanelChangePass.setVisible(true);
		this.add(jpanelChangePass).setBounds(0, 0, JFrameMain.WIDTH_SIZE/2, JFrameMain.HEIGTH_SIZE);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(imageBackground.getImage(), 0, 0, JFrameMain.WIDTH_SIZE, JFrameMain.HEIGTH_SIZE, this);
		g.setColor(new Color(70, 70, 70, 200));
		g.fillRect(0, 0, JFrameMain.WIDTH_SIZE / 2, JFrameMain.HEIGTH_SIZE);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, JFrameMain.WIDTH_SIZE / 2, JFrameMain.HEIGTH_SIZE);
		g.drawImage(imageLogo.getImage(), JFrameMain.WIDTH_SIZE - JFrameMain.WIDTH_SIZE / 3,
				JFrameMain.HEIGTH_SIZE - (140* JFrameMain.HEIGTH_SIZE / 800), JFrameMain.WIDTH_SIZE / 3, 130* JFrameMain.HEIGTH_SIZE / 800, this);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 30*JFrameMain.WIDTH_SIZE/1300));
		g.drawString(Constants.VERSION_1_0, 10* JFrameMain.WIDTH_SIZE / 1300, JFrameMain.HEIGTH_SIZE - (30* JFrameMain.HEIGTH_SIZE / 800));
		super.paint(g);
	}

	public String getUsernameLogin() {
		return jTextFieldUsername.getText();
	}

	public String getPasswordLogin() {
		return new String(jTextFieldPassword.getPassword());
	}

	public void putBlankText() {
		this.jTextFieldUsername.setText("");
		this.jTextFieldPassword.setText("");
	}

	public String getCodeRecovery() {
		return jTextFieldCode.getText();
	}

	public String getPasswordConfirmRecovery() {
		return jTextFieldConfirmNewPass.getText();
	}

	public String getPasswordRecovery() {
		return jTextFieldNewPass.getText();
	}

	public void setUserNameRecovery(String username) {
		this.jTextFieldUser.setText(username);
	}

}
