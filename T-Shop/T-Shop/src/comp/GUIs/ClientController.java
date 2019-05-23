package comp.GUIs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import comp.Access.InvoiceAccess;
import comp.invoice.Invoice;
import comp.users.Client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ClientController implements Initializable {
	final static Logger logger = Logger.getLogger(ClientController.class);


	static Client client = new Client();

	@FXML
	private Label welcomeLbl;

	@FXML
	private Button totBtn;

	@FXML
	private Button neplatitBtn;

	@FXML
	private Button adaugaBtn;
	
	@FXML
	private Button exitBtn;

	@FXML
	private Label restLbl;

	@FXML
	private TableView<Invoice> tableView;

	@FXML
	private TableColumn<Invoice, Integer> facturaClm;

	@FXML
	private TableColumn<Invoice, Integer> produseClm;

	@FXML
	private TableColumn<Invoice, Double> totalClm;

	@FXML
	private TableColumn<Invoice, Boolean> statusClm;
	
	@FXML
	private TableColumn<Invoice, Boolean> acceptClm;
	ObservableList<Invoice> listaFacturi;
	
	Stage stage1 = null;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//afisareFacturi();

		totBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				afisareFacturi();
			}

		});

		neplatitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				afisareFacturiNeplatite();
			}

		});

		adaugaBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					ShopController.client = client;
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Shopping.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.show();
					afisareFacturi();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		
		exitBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					stage1 = (Stage) exitBtn.getScene().getWindow();
					stage1.close();
			    
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Login.fxml"));
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setScene(new Scene(root1));
					stage.show();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	public void afisareFacturi() {
		facturaClm.setText("Numar Factura");
		statusClm.setText("Status");
		totalClm.setText("Total");
		produseClm.setText("Produse");
		acceptClm.setText("Discount Acceptat");
		facturaClm.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceId"));
		totalClm.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("total"));
		statusClm.setCellValueFactory(new PropertyValueFactory<Invoice, Boolean>("status"));
		produseClm.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("nrOfObjects"));
		acceptClm.setCellValueFactory(new PropertyValueFactory<Invoice, Boolean>("accepted"));
		listaFacturi = FXCollections.observableArrayList(InvoiceAccess.displayInvoices(client));
		
		logger.info(listaFacturi);
		tableView.setItems(listaFacturi);
	}

	public void afisareFacturiNeplatite() {
		listaFacturi = FXCollections.observableArrayList(InvoiceAccess.unpaidInvoices(client));
		logger.info(listaFacturi);
		tableView.setItems(listaFacturi);
	}

}