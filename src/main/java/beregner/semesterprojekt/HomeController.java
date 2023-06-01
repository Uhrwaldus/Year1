package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import com.ferrari.finances.dk.bank.InterestRate;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
// Ulrikke
public class HomeController extends Sidebar implements Initializable {
    double udbetaling, indkomst, pris, udregning;
    double måneder;

    @FXML
    private TextField udbetalingInput, indkomstInput, prisInput;
    @FXML
    private Slider periode;
    @FXML
    private Label resultat;
    @FXML
    private ChoiceBox bilInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connecter til databasen gennem HomeModel
        HomeModel database = new HomeModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //henter bilerne til choicebox
        try {
            database.getBiler(bilInput);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        bilInput.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                double price = HomeModel.getPrice((String) newValue);
                prisInput.setText(String.valueOf(price));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void check(javafx.event.ActionEvent actionEvent) {
        //læser værdierne fra textfelterne og laver udregning
        udbetaling = Double.parseDouble(udbetalingInput.getText());
        indkomst = Double.parseDouble(indkomstInput.getText());
        pris = Double.parseDouble(prisInput.getText());
        måneder = periode.getValue();

        // Jonas
        udregning = (pris - udbetaling) / måneder * (InterestRate.i().todaysRate() / 100 + 1);
        resultat.setText(String.format("%.2f", udregning) + "kr.");
    }
    // Jonas
    public void logud(ActionEvent event) throws IOException { logudButton(event); }
    public void createKnap(ActionEvent event) throws IOException { createButton(event); }
    public void offerKnap(ActionEvent event) throws IOException { offerButton(event); }
    public void inventoryKnap(ActionEvent event) throws IOException { inventoryButton(event); }
    public void statsKnap(ActionEvent event) throws IOException { statsButton(event); }
    public void customerKnap(ActionEvent event) throws IOException { customerButton(event); }
}
