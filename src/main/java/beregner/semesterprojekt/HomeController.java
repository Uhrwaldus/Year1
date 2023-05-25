package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import com.ferrari.finances.dk.bank.InterestRate;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private HomeModel database;
    double udbetaling, indkomst, pris, udregning;
    String bil;
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
        database = new HomeModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            database.getBiler(bilInput);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void check(javafx.event.ActionEvent actionEvent) {
        //læser værdierne fra textfelterne
        udbetaling = Double.parseDouble(udbetalingInput.getText());
        indkomst = Double.parseDouble(indkomstInput.getText());
        pris = Double.parseDouble(prisInput.getText());
        måneder = periode.getValue();

        udregning = (pris - udbetaling) / måneder * (InterestRate.i().todaysRate() / 100 + 1);
        resultat.setText(String.format("%.2f", udregning) + "kr.");

        System.out.println( InterestRate.i().todaysRate());
    }

    public void getPris(ActionEvent Event){
        //hent prisen ud fra hvilken bil man vælger i DB

    }
}