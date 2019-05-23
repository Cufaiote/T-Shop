package comp.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import comp.Access.InvoiceAccess;
import comp.Access.UserAccess;
import comp.invoice.Invoice;
import comp.users.User;

public class Server {
	final static Logger logger = Logger.getLogger(Server.class);

	public static void main(String[] args) throws SQLException {

		try (ServerSocket serverSocket = new ServerSocket(6282)) {

			logger.info("Connection works");
			while (true) {

				Socket server = serverSocket.accept();
				logger.info("Just connected to " + server.getRemoteSocketAddress());
				ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(server.getInputStream()));

				String recieved = in.readObject().toString();

				String[] splitted = recieved.split(" ");
				String command = splitted[0];

				String obj = splitted[1];
				ObjectInputStream inFs = new ObjectInputStream(new ByteArrayInputStream(obj.getBytes()));

				if (command.equals("inregistrare")) {
					User user = (User) inFs.readObject();
					logger.info(user.toString());
					
					if (UserAccess.verificareDate(user) != 0) {
						out.writeObject(0);
						out.flush();
					} else {
						if (UserAccess.adaugareDate(user)) {
							out.writeObject(1);
							out.flush();
						} else {
							out.writeObject(0);
							out.flush();
						}
					}
					

				}
				if (command.equals("autentificare")) {
					User user = (User) inFs.readObject();
					logger.info(user.toString());
					System.out.println(user.toString());
					System.out.println(UserAccess.verificareDate(user));
					System.out.println(user.getUsername());
					System.out.println(user.getPassword());
					System.out.println(user.getStatus());
					out.writeObject(UserAccess.verificareDate(user));
					out.flush();

				}
				if (command.equals("cerere")) {
					Invoice invoice = (Invoice) inFs.readObject();
					logger.info(invoice.toString());
					if (InvoiceAccess.createInvoice(invoice)) {
						out.writeObject(1);
						out.flush();
					} else {
						out.writeObject(0);
						out.flush();
					}

				}

			}
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
