package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.ferrari.finances.dk.bank.InterestRate;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OfferController implements Initializable {
    @FXML
    private TableView<Offers> offerTabel;
    @FXML
    TableColumn<Offers, Integer> column1;
    @FXML
    TableColumn<Offers, Date> column2;
    @FXML
    TableColumn<Offers, Integer> column3;
    @FXML
    TableColumn<Offers, String> column4;
    @FXML
    TableColumn<Offers, Integer> column5;
    @FXML
    TableColumn<Offers, Integer> column6;
    @FXML
    TableColumn<Offers, Integer> column7;
    @FXML
    TableColumn<Offers, Integer> column8;
    @FXML
    TableColumn<Offers, Integer> column9;
    @FXML
    TableColumn<Offers, Integer> column10;
    OfferModel database = new OfferModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connecter til databasen gennem HomeModel
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //opsætning af kollonner i tabellen
        column1.setCellValueFactory(new PropertyValueFactory<>("offer_ID"));
        column2.setCellValueFactory(new PropertyValueFactory<>("date"));
        column3.setCellValueFactory(new PropertyValueFactory<>("interest"));
        column4.setCellValueFactory(new PropertyValueFactory<>("credit_rating"));
        column5.setCellValueFactory(new PropertyValueFactory<>("loan_total"));
        column6.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        column7.setCellValueFactory(new PropertyValueFactory<>("duration"));
        column8.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        column8.setCellValueFactory(new PropertyValueFactory<>("salesman_ID"));
        column9.setCellValueFactory(new PropertyValueFactory<>("car_ID"));

        offerTabel.setItems(OfferModel.getDataList());

        try {
            OfferModel.loadOffers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}