package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import com.ferrari.finances.dk.bank.InterestRate;


public class HomeController {
    double udbetaling, indkomst, pris, udregning;
    String bil;
    double måneder;
    private HomeModel db;

    @FXML
    private TextField udbetalingInput, indkomstInput, prisInput;
    @FXML
    private Slider periode;
    @FXML
    private Label resultat;
    @FXML
    private ChoiceBox bilInput;

    public void check(javafx.event.ActionEvent actionEvent) {
        //læser værdierne fra textfelterne
        udbetaling = Double.parseDouble(udbetalingInput.getText());
        indkomst = Double.parseDouble(indkomstInput.getText());
        pris = Double.parseDouble(prisInput.getText());
        måneder = periode.getValue();

        udregning = (pris - udbetaling) / måneder * (InterestRate.i().todaysRate() / 100 + 1);
        resultat.setText(String.format("%.2f", udregning));

        System.out.println( InterestRate.i().todaysRate());

        this.db = new HomeModel();
    }

    public void getPris(ActionEvent Event){
        //hent prisen ud fra hvilken bil man vælger i DB

    }
}