package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.ferrari.finances.dk.bank.InterestRate;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    private TableView<Kunder> kundeTabel;
    @FXML
    TableColumn<Kunder, Integer> column1;
    @FXML
    TableColumn<Kunder, String> column2;
    @FXML
    TableColumn<Kunder, String> column3;
    @FXML
    TableColumn<Kunder, String> column4;
    @FXML
    TableColumn<Kunder, Integer> column5;
    @FXML
    TableColumn<Kunder, String> column6;
    @FXML
    TableColumn<Kunder, String> column7;
    @FXML
    TableColumn<Kunder, Integer> column8;
    @FXML
    private TextField navnField;
    @FXML
    private TextField efternavnField;
    @FXML
    private TextField tlfnrField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addresseField;
    @FXML
    private TextField postnrField;

    private Kunder kunder;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connecter til databasen gennem HomeModel
        CustomerModel database = new CustomerModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //opsætning af kollonner i tabellen
        column1.setCellValueFactory(new PropertyValueFactory<>("ID"));
        column2.setCellValueFactory(new PropertyValueFactory<>("Navn"));
        column3.setCellValueFactory(new PropertyValueFactory<>("Efternavn"));
        column4.setCellValueFactory(new PropertyValueFactory<>("Email"));
        column5.setCellValueFactory(new PropertyValueFactory<>("Tlfnr"));
        column6.setCellValueFactory(new PropertyValueFactory<>("Addresse"));
        column7.setCellValueFactory(new PropertyValueFactory<>("By"));
        column8.setCellValueFactory(new PropertyValueFactory<>("Postnr"));

        kundeTabel.setItems(CustomerModel.getDataList());

        try {
            CustomerModel.loadCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void opretButtonAction(ActionEvent event) {
        //hent værdierne fra textfields i view
        String navn = navnField.getText();
        String efternavn = efternavnField.getText();
        String tlfnr = tlfnrField.getText();
        String email = emailField.getText();
        String adresse = addresseField.getText();
        String postnr = postnrField.getText();

        CustomerModel.opretKunde(column1, column2, column3, column4, column5, column6, column7, column8);

    }
}