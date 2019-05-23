package comp.users;

import java.util.ArrayList;
import comp.invoice.Invoice;

public class SalesPersonnel extends User {

	/**
	 * 
	 */

	public static boolean flag;
	static Invoice invoice;
	private static final long serialVersionUID = 1L;

	public SalesPersonnel(int userId, String username, String password, ArrayList<Invoice> invoiceList) {
		this.setUserId(userId);
		this.setUsername(username);
		this.setPassword(password);
		invoiceList = new ArrayList<>(invoiceList);
	}

	public SalesPersonnel(int userId, String username, String password) {
		super(userId, username, password);
	}

	public SalesPersonnel() {
		super();
	}

}
