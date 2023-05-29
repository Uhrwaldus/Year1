package beregner.semesterprojekt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
// Ulrikke

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
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<>("date"));
        column3.setCellValueFactory(new PropertyValueFactory<>("interest"));
        column4.setCellValueFactory(new PropertyValueFactory<>("rating"));
        column5.setCellValueFactory(new PropertyValueFactory<>("loan"));
        column6.setCellValueFactory(new PropertyValueFactory<>("deposit"));
        column7.setCellValueFactory(new PropertyValueFactory<>("duration"));
        column8.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        column9.setCellValueFactory(new PropertyValueFactory<>("saleID"));
        column10.setCellValueFactory(new PropertyValueFactory<>("carID"));

        offerTabel.setItems(OfferModel.getDataList());

        try {
            OfferModel.loadOffers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleExportButton() {
        // hent den valgte række fra tabellen
        Offers selectedOffer = offerTabel.getSelectionModel().getSelectedItem();
        if (selectedOffer == null) {
            return;
        }

        StringBuilder csvData = new StringBuilder();

        csvData.append("ID,Dato,Rente,Kredit_vurdering,Lån,Udbetaling,Periode,KundeID,SælgerID,BilID\n");


        csvData.append(selectedOffer.getId()).append(",")
                .append(selectedOffer.getDate()).append(",")
                .append(selectedOffer.getInterest()).append(",")
                .append(selectedOffer.getRating()).append(",")
                .append(selectedOffer.getLoan()).append(",")
                .append(selectedOffer.getDeposit()).append(",")
                .append(selectedOffer.getDuration()).append(",")
                .append(selectedOffer.getCustomerID()).append(",")
                .append(selectedOffer.getSaleID()).append(",")
                .append(selectedOffer.getCarID()).append("\n");

        try (FileWriter fileWriter = new FileWriter("Eksporteret_tilbud.csv")) {
            fileWriter.append(csvData);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}