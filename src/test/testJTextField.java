package test;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class testJTextField {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(380,300);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.CYAN);
		
		JLabel jLabel1 = new JLabel("hola");
		jLabel1.setBackground(Color.RED);
		
		frame.add(jLabel1);
		frame.setVisible(true);
	}
	
}
