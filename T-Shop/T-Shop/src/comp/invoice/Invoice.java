package comp.invoice;

import java.io.Serializable;

public class Invoice implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int invoiceId;
	int clientId;
	int nrOfObjects;
	double total;
	boolean status;
	int discount;
	boolean accepted;

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setIdFactura(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getNrOfObjects() {
		return nrOfObjects;
	}

	public void setNrOfObjects(int nrOfObjects) {
		this.nrOfObjects = nrOfObjects;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getClientId() {
		return clientId;
	}

	public void setCustomerId(int idClient) {
		this.clientId = idClient;
	}

	public Invoice(int invoiceId, int nrOfObjects, double total, boolean status, int clientId, boolean accepted) {
		super();
		this.invoiceId   = invoiceId;
		this.nrOfObjects = nrOfObjects;
		this.total       = total;
		this.status      = status;
		this.clientId    = clientId;
		this.accepted    = accepted;
	}

	public Invoice() {

	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + invoiceId + ", idClient=" + clientId + ", nrObiecte=" + nrOfObjects
				+ ", total=" + total + ", status=" + status + "]";
	}

}