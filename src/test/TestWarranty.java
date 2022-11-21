package test;

import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JFrame;

import view.cashier.JPanelTableWarranty;

public class TestWarranty {
	static ArrayList<Object[]> listbills = new ArrayList<Object[]>();
	static ArrayList<Object[]> productsInBill = new ArrayList<Object[]>();

	public static void main(String[] args) {
		add();
		JPanelTableWarranty jPanelTableBill = new JPanelTableWarranty(listbills.get(0));
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1700, 650);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(jPanelTableBill);
	}

	private static void add() {
		productsInBill.add(new Object[] { 1001, 3, "Mouse x", "Activa", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 3, "Mouse x", "Activa", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 3, "Mouse x", "Activa", 123123.32, 1233212.32 });
		productsInBill.add(new Object[] { 1001, 3, "Mouse x", "Activa", 123123.32, 1233212.32 });

		listbills.add(new Object[] { LocalDate.of(2022, 2, 21), // fecha factura
				20221001, // numero factura
				"Daniel Felipe Suarez Bohorquez", // cliente
				"CC", 1002587073, // id cliente
				"Crisitan David Parada Martinez", // cajero
				1002365941, // id cajero
				"Minoritario", "Efectivo", productsInBill, // subtotal
				19, // iva porcentaje
				167657839.03+"", // subtotal
				1237123.21+"", // iva
				123141231.43+"" // total
		});
	}
}
