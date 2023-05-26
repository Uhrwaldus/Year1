package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static beregner.semesterprojekt.ReviewModel.acceptOffer;

public class ReviewController implements Initializable {

    private ReviewModel database;
    private ReviewModel model = new ReviewModel();
    @FXML
    private TextField date, interest, credit, loan_total, deposit, phone, carName, duration, carPrice, custName;
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
            ReviewModel.getOfferInfo(choiceBox, date, interest, credit, loan_total, deposit,
                    duration, carName, carPrice, phone, custName);
        });
    }
    public void acceptOfferOnClick(ActionEvent event) throws SQLException {
        Integer offerID = (Integer) choiceBox.getValue();
        if (offerID != null) {
            try {
                ReviewModel.acceptOffer(offerID, 2);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void denyOfferOnClick(ActionEvent event) {
        Integer offerID = (Integer) choiceBox.getValue();
        if (offerID != null) {
            try {
                ReviewModel.denyOffer(offerID, 3);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}



