package view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import com.toedter.calendar.JCalendar;
import model.TextPrompt;
import view.JFrameMain;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JPanelOptionSalesHistory extends JPanel {

	private GridBagConstraints gbc;
	private ImageIcon image1;
	private ImageIcon image2;
	private ImageIcon image3;
	private ImageIcon image4;
	private ImageIcon image5;
	private ImageIcon image6;
	private JLabel jLabelImage;
	private JLabel jLabelTittle;
	private JLabel jLabelInitialDate;
	private JLabel jLabelFinalDate;
	private JPanel jPanelContainer;
	private JTextField jTextFieldInitialDate;
	private JTextField jTextFieldFinalDate;
	private JButtonRound buttonImage1;
	private JButtonRound buttonImage2;
	private JButtonRound buttonImage3;
	private JButtonRound buttonImage4;
	private JButtonRound buttonImage5;
	private JButtonRound buttonImage6;
	private JButton buttonCalendarI;
	private JButton buttonCalendarF;
	private int count;
	private Timer timer;
	private JCalendar jCalendarI;
	private JCalendar jCalendarF;
	private JPopupMenu jPopupMenuI;
	private JPopupMenu jPopupMenuF;

	/**
	 * Constructor de JPanelOptionSalesHistory
	 */
	public JPanelOptionSalesHistory() {
		super(new GridBagLayout());
		this.count = 1;
		this.jCalendarI = new JCalendar();
		this.jCalendarF = new JCalendar();
		this.buttonCalendarI = new JButton(new ImageIcon(
				new ImageIcon("res/calendar.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		this.buttonCalendarF = new JButton(new ImageIcon(
				new ImageIcon("res/calendar.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		this.gbc = new GridBagConstraints();
		this.jPanelContainer = new JPanel(new GridBagLayout());
		this.buttonImage1 = new JButtonRound();
		this.buttonImage2 = new JButtonRound();
		this.buttonImage3 = new JButtonRound();
		this.buttonImage4 = new JButtonRound();
		this.buttonImage5 = new JButtonRound();
		this.buttonImage6 = new JButtonRound();

		this.image1 = new ImageIcon(
				new ImageIcon("res/carr1.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH));
		this.image2 = new ImageIcon(
				new ImageIcon("res/carr2.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH));
		this.image3 = new ImageIcon(
				new ImageIcon("res/carr3.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH));
		this.image4 = new ImageIcon(
				new ImageIcon("res/carr4.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH));
		this.image5 = new ImageIcon(
				new ImageIcon("res/carr5.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH));
		this.image6 = new ImageIcon(
				new ImageIcon("res/carr6.png").getImage().getScaledInstance(450, 450, Image.SCALE_SMOOTH));

		this.jLabelImage = new JLabel(image1);
		this.jLabelTittle = new JLabel("Reporte de Ventas", JLabel.CENTER);
		this.jLabelInitialDate = new JLabel("Fecha inicial:");
		this.jLabelFinalDate = new JLabel("Fecha Final:");
		this.jTextFieldInitialDate = new JTextField();
		this.jTextFieldFinalDate = new JTextField();

		jPopupMenuI = new JPopupMenu();
		jPopupMenuI.setPreferredSize(new Dimension(300, 270));
		jPopupMenuI.setLocation(1550, 600);
		jPopupMenuF = new JPopupMenu();
		jPopupMenuF.setPreferredSize(new Dimension(300, 270));
		jPopupMenuF.setLocation(1550, 710);
		jPopupMenuI.add(jCalendarI);
		jPopupMenuF.add(jCalendarF);
		init();
	}

	/**
	 * Metodo que inicializa las propiedades de este panel
	 */
	private void init() {
		this.setOpaque(false);
		this.setBorder(new LineBorder(new Color(127, 96, 0), 5 * JFrameMain.WIDTH_SIZE / 1920));
		this.setPreferredSize(new Dimension(1000 * JFrameMain.WIDTH_SIZE / 1920, 0));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		initGBC();
		initTimer();
		initPropContainer();
	}

	/**
	 * Metodo que inicializa las propiedades del panel contenedor
	 */
	private void initPropContainer() {
		this.jPanelContainer.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = 1;
		this.jPanelContainer.add(jLabelInitialDate, gbc);
		gbc.weightx = 1;
		gbc.gridy = 1;
		this.jPanelContainer.add(jTextFieldInitialDate, gbc);
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.insets.top = 50;
		this.jPanelContainer.add(jLabelFinalDate, gbc);
		gbc.weightx = 1;
		gbc.insets.top = 0;
		gbc.gridy = 3;
		this.jPanelContainer.add(jTextFieldFinalDate, gbc);
	}

	/**
	 * Metodo que inicializa el timer del carrusel
	 */
	private void initTimer() {
		buttonImage1.setPreesed(true);
		timer = new Timer(2500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeImage();
				count = count + 1 == 7 ? 1 : count + 1;
			}

		});
		timer.start();
	}

	/**
	 * Metodo que agrega los componentes 
	 */
	private void initGBC() {
		initPropComp();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = 1;
		gbc.gridwidth = 7;
		gbc.insets = new Insets(3, 0, 30, 0);
		this.add(jLabelTittle, gbc);
		gbc.insets = new Insets(40, 60, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 6;
		this.add(jLabelImage, gbc);
		gbc.insets = new Insets(0, 190, 150, 5);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.fill = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		this.add(buttonImage1, gbc);
		gbc.insets = new Insets(0, 0, 150, 0);
		gbc.gridx = 1;
		gbc.gridy = 2;
		this.add(buttonImage2, gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		this.add(buttonImage3, gbc);
		gbc.gridx = 3;
		gbc.gridy = 2;
		this.add(buttonImage4, gbc);
		gbc.gridx = 4;
		gbc.gridy = 2;
		this.add(buttonImage5, gbc);
		gbc.gridx = 5;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 150, 100);
		this.add(buttonImage6, gbc);
		gbc.insets = new Insets(40, 30, 150, 60);
		gbc.fill = 1;
		gbc.weightx = 1;
		gbc.gridheight = 2;
		gbc.gridx = 6;
		gbc.gridy = 1;
		this.add(jPanelContainer, gbc);
	}

	/**
	 * Metodo que cambia la imagen del carrusel
	 */
	private void changeImage() {
		switch (count) {
		case 1:
			jLabelImage.setIcon(image1);
			buttonImage1.setPreesed(true);
			buttonImage2.setPreesed(false);
			buttonImage3.setPreesed(false);
			buttonImage4.setPreesed(false);
			buttonImage5.setPreesed(false);
			buttonImage6.setPreesed(false);
			break;
		case 2:
			jLabelImage.setIcon(image2);
			buttonImage1.setPreesed(false);
			buttonImage2.setPreesed(true);
			buttonImage3.setPreesed(false);
			buttonImage4.setPreesed(false);
			buttonImage5.setPreesed(false);
			buttonImage6.setPreesed(false);
			break;
		case 3:
			jLabelImage.setIcon(image3);
			buttonImage1.setPreesed(false);
			buttonImage2.setPreesed(false);
			buttonImage3.setPreesed(true);
			buttonImage4.setPreesed(false);
			buttonImage5.setPreesed(false);
			buttonImage6.setPreesed(false);
			break;
		case 4:
			jLabelImage.setIcon(image4);
			buttonImage1.setPreesed(false);
			buttonImage2.setPreesed(false);
			buttonImage3.setPreesed(false);
			buttonImage4.setPreesed(true);
			buttonImage5.setPreesed(false);
			buttonImage6.setPreesed(false);
			break;
		case 5:
			jLabelImage.setIcon(image5);
			buttonImage1.setPreesed(false);
			buttonImage2.setPreesed(false);
			buttonImage3.setPreesed(false);
			buttonImage4.setPreesed(false);
			buttonImage5.setPreesed(true);
			buttonImage6.setPreesed(false);
			break;
		case 6:
			jLabelImage.setIcon(image6);
			buttonImage1.setPreesed(false);
			buttonImage2.setPreesed(false);
			buttonImage3.setPreesed(false);
			buttonImage4.setPreesed(false);
			buttonImage5.setPreesed(false);
			buttonImage6.setPreesed(true);
			break;
		}
	}

	/**
	 * Metodo que inicializa las propiedades de los componentes
	 */
	private void initPropComp() {
		this.buttonCalendarI.setRolloverEnabled(false);
		this.buttonCalendarF.setRolloverEnabled(false);
		this.buttonCalendarI.setFocusable(false);
		this.buttonCalendarF.setFocusable(false);
		this.buttonCalendarI.setContentAreaFilled(false);
		this.buttonCalendarF.setContentAreaFilled(false);
		this.buttonCalendarI.setBorderPainted(false);
		this.buttonCalendarF.setBorderPainted(false);
		this.buttonCalendarI.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.buttonCalendarF.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.buttonCalendarI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPopupMenuI.setVisible(!jPopupMenuI.isVisible());
				jPopupMenuF.setVisible(false);
			}
		});
		this.buttonCalendarF.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jPopupMenuF.setVisible(!jPopupMenuF.isVisible());
				jPopupMenuI.setVisible(false);
			}
		});

		this.jLabelTittle.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 45 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelInitialDate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35 * JFrameMain.WIDTH_SIZE / 1920));
		this.jLabelFinalDate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 35 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldInitialDate.setFont(new Font("Arial", Font.ITALIC, 30 * JFrameMain.WIDTH_SIZE / 1920));
		this.jTextFieldFinalDate.setFont(new Font("Arial", Font.ITALIC, 30 * JFrameMain.WIDTH_SIZE / 1920));

		this.jTextFieldInitialDate.setOpaque(false);
		this.jTextFieldFinalDate.setOpaque(false);
		this.jTextFieldInitialDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.jTextFieldFinalDate.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.jTextFieldInitialDate.setBorder(new LineBorder(Color.BLACK, 3));
		this.jTextFieldFinalDate.setBorder(new LineBorder(Color.BLACK, 3));

		this.jTextFieldFinalDate.setLayout(new BorderLayout());
		this.jTextFieldInitialDate.setLayout(new BorderLayout());

		this.jTextFieldFinalDate.setEditable(false);
		this.jTextFieldInitialDate.setEditable(false);

		TextPrompt textPrompt = new TextPrompt("Digite la fecha inicial", jTextFieldInitialDate);
		textPrompt.changeAlpha(0.25f);
		textPrompt.changeStyle(Font.ITALIC);
		TextPrompt textPrompt2 = new TextPrompt("Digite la fecha final", jTextFieldFinalDate);
		textPrompt2.changeAlpha(0.25f);
		textPrompt2.changeStyle(Font.ITALIC);

		this.jTextFieldInitialDate.add(buttonCalendarI, BorderLayout.EAST);
		this.jTextFieldFinalDate.add(buttonCalendarF, BorderLayout.EAST);

		this.jLabelTittle.setForeground(Color.BLACK);
		this.jLabelInitialDate.setForeground(Color.BLACK);
		this.jLabelFinalDate.setForeground(Color.BLACK);
		this.jTextFieldInitialDate.setForeground(Color.BLACK);
		this.jTextFieldFinalDate.setForeground(Color.BLACK);

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				changeFocusImage(((JButtonRound) e.getSource()).getMnemonic());
			}

		};

		this.buttonImage1.setMnemonic(1);
		this.buttonImage2.setMnemonic(2);
		this.buttonImage3.setMnemonic(3);
		this.buttonImage4.setMnemonic(4);
		this.buttonImage5.setMnemonic(5);
		this.buttonImage6.setMnemonic(6);

		this.buttonImage1.addActionListener(actionListener);
		this.buttonImage2.addActionListener(actionListener);
		this.buttonImage3.addActionListener(actionListener);
		this.buttonImage4.addActionListener(actionListener);
		this.buttonImage5.addActionListener(actionListener);
		this.buttonImage6.addActionListener(actionListener);

		jCalendarI.addPropertyChangeListener(new PropertyChangeListener() {
			@Override

			public void propertyChange(PropertyChangeEvent evt) {
				LocalDate date = jCalendarI.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				jTextFieldInitialDate.setText(String.format("%02d", date.getDayOfMonth()) + "/"
						+ String.format("%02d", date.getMonthValue()) + "/" + date.getYear());
			}
		});
		
		jCalendarF.addPropertyChangeListener(new PropertyChangeListener() {
			@Override

			public void propertyChange(PropertyChangeEvent evt) {
				LocalDate date = jCalendarF.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				jTextFieldFinalDate.setText(String.format("%02d", date.getDayOfMonth()) + "/"
						+ String.format("%02d", date.getMonthValue()) + "/" + date.getYear());
			}
		});
	}

	/**
	 * Metodo que cambia la imagen por la clickeada
	 * @param mnemonic indice de imagen
	 */
	private void changeFocusImage(int mnemonic) {
		count = mnemonic;
		changeImage();
	}

	/**
	 * Metodo que desactiva los calendarios
	 */
	public void desactiveCalendars() {
		this.jPopupMenuI.setVisible(false);
		this.jPopupMenuF.setVisible(false);
	}
	
	/**
	 * Metodo que subraya las cajas de texto
	 * @param index indice 
	 * @param colorOption color
	 */
	public void setBorderTo(int index, int colorOption) {
		if (index == 0) {
			if (colorOption==0) {
				this.jTextFieldInitialDate.setBorder(new LineBorder(Color.BLACK, 3));
			}else {
				this.jTextFieldInitialDate.setBorder(new LineBorder(Color.RED, 3));
			}
		}else {
			if (colorOption==0) {
				this.jTextFieldFinalDate.setBorder(new LineBorder(Color.BLACK, 3));
			}else {
				this.jTextFieldFinalDate.setBorder(new LineBorder(Color.RED, 3));
			}
		}
	}
	
	/*
	 * Metodo que obtiene la fecha inicial
	 */
	public String getDateI() {
		return jTextFieldInitialDate.getText();
	}
	/**
	 * Metodo que obtiene la fecha final
	 * @return fecha final
	 */
	public String getDateF() {
		return jTextFieldFinalDate.getText();
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		GradientPaint gradientPaint = new GradientPaint(0f, 0f, new Color(192, 145, 2), getWidth() / 3 * 2, 0f,
				new Color(255, 230, 153));
		g.setPaint(gradientPaint);
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paint(graphics);
	}
}
