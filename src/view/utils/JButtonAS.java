package view.utils;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public class JButtonAS extends JButton {

	private static final long serialVersionUID = 1L;
	private boolean isAnimated;
	private Dimension animatedSize;
	private Dimension sizeButton;
	private Icon iconNormal;
	private Icon iconResize;

	/**
	 * Constructor de JButtonAS
	 * @param text texto del boton
	 * @param border color del borde
	 * @param stroke grosor del borde
	 * @param size tamaño
	 */
	public JButtonAS(String text, Color border, int stroke, Dimension size) {
		super(text);
		this.isAnimated = false;
		initProperties(border, stroke, size);
	}

	/**
	 * Metodo que cambia el tamaño de la animacion del boton
	 * @param animatedSize tamaño de la animacion
	 */
	public void setAnimatedSize(Dimension animatedSize) {
		this.animatedSize = animatedSize;
	}

	/**
	 * Metodo que inicia las propiedades del boton
	 * @param border borde 
	 * @param stroke grosor
	 * @param size tamaño
	 */
	private void initProperties(Color border, int stroke, Dimension size) {
		this.sizeButton = size;
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setRolloverEnabled(false);
		this.setBorder(new LineBorder(border, stroke));
		this.setSize(size);
		this.setPreferredSize(size);
		this.setFocusable(false);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isAnimated) {
					if (iconResize!=null) {
						setSize(animatedSize.width, animatedSize.height);
						setIcon(iconResize);
					}else {
						setSize(animatedSize.width, animatedSize.height);
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (isAnimated) {
					if (iconResize!=null) {
						setSize(sizeButton.width, sizeButton.height);
						setIcon(iconNormal);
					}else {
						setSize(sizeButton.width, sizeButton.height);
					}
				}
			}
		});
	}

	/**
	 * 
	 * Constructor de JButtonAS
	 * @param icon icono 
	 * @param border color del borde
	 * @param stroke grosor
	 * @param size tamaño
	 */
	public JButtonAS(ImageIcon icon, Color border, int stroke, Dimension size) {
		super(icon);
		this.iconNormal = icon;
		this.iconResize = new ImageIcon(icon.getImage().getScaledInstance(icon.getIconWidth() + 10,
				icon.getIconHeight() + 10, Image.SCALE_SMOOTH));
		this.isAnimated = false;
		initProperties(border, stroke, size);
	}

	/**
	 * Metodo que cambia si es animado o no
	 * @param b true o false
	 */
	public void setAnimated(boolean b) {
		isAnimated = b;
	}
}
