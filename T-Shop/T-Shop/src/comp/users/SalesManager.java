package comp.users;

import java.util.Random;
//import java.util.concurrent.ThreadLocalRandom;

import comp.Access.InvoiceAccess;
import comp.invoice.Invoice;

public final class SalesManager extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Invoice invoice;

	public SalesManager(String username) {
		super(username);
	}

	public SalesManager() {
		super();
		this.setUsername("God");
	}

	public static void process(Invoice invoice, int discount) {
		boolean flag = false;
		Random rand = new Random(); 
		int randomNum= rand.nextInt(25); 

		if(randomNum > 10) {
			flag = !flag;
			invoice.setAccepted(true);
		}

		if (flag) {
			InvoiceAccess.updateTotal(invoice, discount);
		} else {
			InvoiceAccess.updateTotal(invoice, 10);
		}
	}
}
