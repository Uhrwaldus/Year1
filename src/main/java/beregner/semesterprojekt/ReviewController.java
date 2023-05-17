package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReviewController implements Initializable {

    private ReviewModel database;
    private ReviewModel model = new ReviewModel();
    @FXML
    private TextField date, interest, credit, loan_total, deposit, custName, phone, salesName, carName, duration;
    @FXML
    private ChoiceBox choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connecter til databasen gennem LoginModel
        ReviewModel database = new ReviewModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.database = database;
        model.getOffer(choiceBox);
        choiceBox.setOnAction(event -> {
            int selectedID = (int) choiceBox.getValue();
            ReviewModel.getInfo(choiceBox, date, interest, credit, loan_total, deposit, duration);
        });
    }
}


