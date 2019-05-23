package comp.GUIs;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import comp.Access.InvoiceAccess;
import comp.invoice.Invoice;
import comp.users.SalesPersonnel;
import comp.users.SalesManager;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SalesPersonnelController implements Initializable {
	final static Logger logger = Logger.getLogger(SalesPersonnelController.class);

	static SalesPersonnel staff = new SalesPersonnel();

	@FXML
	private Button refreshBtn;

	@FXML
	private Button acceptBtn;
	
	@FXML
	private Button exitBtn;

	@FXML
	private TextField idTxt;

	@FXML
	private TextField discountTxt;

	@FXML
	private TableView<Invoice> tableView;

	@FXML
	private TableColumn<Invoice, Integer> facturaClm;

	@FXML
	private TableColumn<Invoice, Integer> obiecteClm;

	@FXML
	private TableColumn<Invoice, Double> totalClm;

	@FXML
	private TableColumn<Invoice, Boolean> statusClm;
	
	@FXML
	private TableColumn<Invoice, Boolean> acceptClm;
	ObservableList<Invoice> listaObiecte;
	
	Stage stage1 = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		afiseazaFacturi();
		refreshBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				afiseazaFacturi();
			}

		});

		acceptBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (!discountTxt.getText().equals("") && Integer.parseInt(discountTxt.getText()) <= 10) {
					Invoice invoice = tableView.getSelectionModel().getSelectedItem();
					InvoiceAccess.updateTotal(invoice, Integer.parseInt(discountTxt.getText()));
				} else {
					Invoice invoice = tableView.getSelectionModel().getSelectedItem();
					SalesManager.process(invoice, Integer.parseInt(discountTxt.getText()));
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

	public void afiseazaFacturi() {
		facturaClm.setText("Nr. Factura");
		statusClm.setText("Status");
		totalClm.setText("Total");
		obiecteClm.setText("Nr. Produse");
		acceptClm.setText("Discount Acceptat");
		facturaClm.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("invoiceId"));
		totalClm.setCellValueFactory(new PropertyValueFactory<Invoice, Double>("total"));
		statusClm.setCellValueFactory(new PropertyValueFactory<Invoice, Boolean>("status"));
		obiecteClm.setCellValueFactory(new PropertyValueFactory<Invoice, Integer>("nrOfObjects"));
		acceptClm.setCellValueFactory(new PropertyValueFactory<Invoice, Boolean>("accepted"));
		listaObiecte = FXCollections.observableArrayList(InvoiceAccess.rejectedInvoices());
		logger.info(listaObiecte);

		tableView.setItems(listaObiecte);

	}

}

