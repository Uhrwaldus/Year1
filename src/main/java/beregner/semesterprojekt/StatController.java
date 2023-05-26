package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatController extends Sidebar implements Initializable {
    private StatModel database;

    @FXML
    private TextField Biler, Lån;
    @FXML
    private ChoiceBox<String> Sælger, Måned;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database = new StatModel();
        try {
            database.Connect();
            populateSælgerChoiceBox();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateSælgerChoiceBox() {
        try {
            List<String> sellers = database.retrieveSellersFromDatabase();
            Sælger.getItems().addAll(sellers);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showStats(ActionEvent event) throws SQLException {
        String selectedSeller = Sælger.getValue();
        if (selectedSeller != null) {
            int carsSold = database.retrieveNumberOfCarsSold(selectedSeller);
            int loansGiven = database.retrieveNumberOfLoansGiven(selectedSeller);

            Biler.setText(String.valueOf(carsSold));
            Lån.setText(String.valueOf(loansGiven));
        }
    }
    public void logud(ActionEvent event) throws IOException { logudButton(event); }
    public void createKnap(ActionEvent event) throws IOException { createButton(event); }
    public void offerKnap(ActionEvent event) throws IOException { offerButton(event); }
    public void inventoryKnap(ActionEvent event) throws IOException { inventoryButton(event); }
    public void statsKnap(ActionEvent event) throws IOException { statsButton(event); }
    public void customerKnap(ActionEvent event) throws IOException { customerButton(event); }
}







