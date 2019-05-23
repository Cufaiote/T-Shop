package comp.GUIs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.log4j.Logger;
import comp.invoice.Invoice;
import comp.users.Client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ShopController implements Initializable {
	final static Logger logger = Logger.getLogger(ShopController.class);

	@FXML
	private Label titleLbl;

	@FXML
	private Label produseLbl;

	@FXML
	private Label totalLbl;

	@FXML
	private TextField produseTxt;

	@FXML
	private TextField totalTxt;

	@FXML
	private Button cumparaBtn;

	static Client client;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cumparaBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String[] aux = totalTxt.getText().split("      ");
				Double total = Double.parseDouble(aux[0]);
				Invoice invoice = new Invoice(5, Integer.parseInt(produseTxt.getText()), total, false,
						client.getUserId(), false);
				logger.info(invoice);
				try {
					Socket socket = new Socket("localhost", 6282);

					ByteArrayOutputStream bo = new ByteArrayOutputStream();
					ObjectOutputStream outFs = new ObjectOutputStream(bo);
					outFs.writeObject(invoice);
					outFs.flush();
					String test = bo.toString();
					ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
					ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

					String toSendObject = "cerere " + test;

					out.writeObject(toSendObject);
					out.flush();
					logger.info("Object sent");
					logger.info(invoice.toString());

					int raspuns = (int) in.readObject();
					if (raspuns == 1) {
						logger.info("Factura a fost inregistrata!");

					} else {
						logger.info("????");}
					socket.close();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					
				}
			}

		});
		

	totalTxt.setOnMouseClicked(new EventHandler<MouseEvent>() {
		// String total = new String();
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if (!produseTxt.getText().equals("")) {
				int prodNr = Integer.parseInt(produseTxt.getText());
				double random = ThreadLocalRandom.current().nextDouble(5, 10);
				String total = String.valueOf(prodNr * random);
				totalTxt.setText(total + "      RON");
			}
		}

	});

}

}
