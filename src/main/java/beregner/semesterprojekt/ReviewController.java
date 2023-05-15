package beregner.semesterprojekt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReviewController implements Initializable {
    private ReviewModel database;
    @FXML
    private TextField date, custName, phone, interest, salesName, carName, price, downPay, credit, duration;
    @FXML
    private ChoiceBox choiceBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            database.getOffer(choiceBox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
