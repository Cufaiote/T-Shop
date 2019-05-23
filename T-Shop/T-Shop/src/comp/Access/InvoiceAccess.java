package comp.Access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import comp.invoice.Invoice;
import comp.users.Client;

public class InvoiceAccess {
	final static Logger logger = Logger.getLogger(InvoiceAccess.class);

	public static boolean createInvoice(Invoice invoice) {
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Invoices VALUES (?, ?, ?, ?, ?, ?)");
			ps.setString(1, null);
			ps.setInt(2, invoice.getNrOfObjects());
			ps.setDouble(3, invoice.getTotal());
			ps.setBoolean(4, invoice.isStatus());
			ps.setInt(5, invoice.getClientId());
			ps.setBoolean(6, invoice.isAccepted());
			ps.execute();
			logger.info("Factura adaugata cu succes!");

			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Adaugare nereusita!");
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean updateTotal(Invoice invoice, int discount) {
		Double newPrice = invoice.getTotal() - (invoice.getTotal() / discount);
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn
					.prepareStatement("UPDATE Invoices SET total = ?, status = 1 WHERE invoiceId = ? ");
			ps.setDouble(1, newPrice);
			ps.setInt(2, invoice.getInvoiceId());
			ps.execute();
			logger.info("Factura a fost actualizata cu succes!");
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Actualizarea facturii a esuat!");
			e.printStackTrace();
		}
		return false;
	}

	public static List<Invoice> acceptedInvoices(Client client) {
		List<Invoice> invoicesList = new ArrayList<>();
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Invoices WHERE userId = ? AND accepted = ?");
			ps.setInt(1, client.getUserId());
			ps.setBoolean(2, true);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// return true;
				Invoice facturaAux = new Invoice();
				facturaAux.setIdFactura(rs.getInt(1));
				facturaAux.setNrOfObjects(rs.getInt(2));
				facturaAux.setTotal(rs.getDouble(3));
				facturaAux.setStatus(rs.getBoolean(4));
				invoicesList.add(facturaAux);

			}
			logger.info("Toate facturile acceptate au fost selectate cu succes!");
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Selectarea tuturor facturilor acceptate a esuat!");
			e.printStackTrace();
		}

		return invoicesList;
	}

	public static List<Invoice> rejectedInvoices() {

		List<Invoice> invoicesList = new ArrayList<>();
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Invoices WHERE accepted = ?");

			ps.setBoolean(1, false);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// return true;
				Invoice facturaAux = new Invoice();
				facturaAux.setIdFactura(rs.getInt(1));
				facturaAux.setNrOfObjects(rs.getInt(2));
				facturaAux.setTotal(rs.getDouble(3));
				facturaAux.setStatus(rs.getBoolean(4));
				invoicesList.add(facturaAux);
			}
			logger.info("Toate facturile respinse au fost selectate cu succes!");

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Eroare la selectarea facturilor respinse!");

			e.printStackTrace();
		}

		return invoicesList;
	}

	public static List<Invoice> unpaidInvoices(Client client) {
		List<Invoice> unpaidInvoicesList = new ArrayList<>();
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Invoices WHERE userId = ? AND status = ?");
			ps.setInt(1, client.getUserId());
			ps.setBoolean(2, false);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// return true;
				Invoice facturaAux = new Invoice();
				facturaAux.setIdFactura(rs.getInt(1));
				facturaAux.setNrOfObjects(rs.getInt(2));
				facturaAux.setTotal(rs.getDouble(3));
				facturaAux.setStatus(rs.getBoolean(4));
				unpaidInvoicesList.add(facturaAux);
			}
			logger.info("Toate facturile neplatite selectate cu succes!");
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Eroare la selectarea facturilor neplatite!");

			e.printStackTrace();
		}

		return unpaidInvoicesList;
	}
	
	public static List<Invoice> displayInvoices(Client client) {
		List<Invoice> invoiceList = new ArrayList<>();
		String url = "jdbc:sqlite:D:\\info\\T-Shop\\T-Shop\\TShopDatabase.db";
		try {
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Invoices WHERE userId = ?");
			ps.setInt(1, client.getUserId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// return true;
				Invoice facturaAux = new Invoice();
				facturaAux.setIdFactura(rs.getInt(1));
				facturaAux.setNrOfObjects(rs.getInt(2));
				facturaAux.setTotal(rs.getDouble(3));
				facturaAux.setStatus(rs.getBoolean(4));
				invoiceList.add(facturaAux);
			}
			logger.info("Toate facturile selectate cu succes!");
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error("Eroare la selectarea tuturor facturilor!");

			e.printStackTrace();
		}

		return invoiceList;
	}
	
}
