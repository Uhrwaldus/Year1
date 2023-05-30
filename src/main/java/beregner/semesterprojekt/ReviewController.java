package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static beregner.semesterprojekt.ReviewModel.acceptOffer;
// Jonas
public class ReviewController extends Sidebar implements Initializable {

    private ReviewModel database;
    private ReviewModel model = new ReviewModel();
    @FXML
    private TextField date, interest, credit, loan_total, deposit, phone, carName, duration, carPrice, custName;
    @FXML
    private Label info;
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
            //Sætter status_ID til 2, da den dermed står som godkendt i databasen
            try {
                ReviewModel.acceptOffer(offerID, 2);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        info.setText("Tilbuddet er hermed godkendt");

        date.clear();
        custName.clear();
        phone.clear();
        interest.clear();
        duration.clear();
        carName.clear();
        carPrice.clear();
        deposit.clear();
        credit.clear();
        loan_total.clear();
    }
    public void denyOfferOnClick(ActionEvent event) {
        Integer offerID = (Integer) choiceBox.getValue();
        if (offerID != null) {
            //Sætter status_ID til 3, da den dermed står som afvist i databasen
            try {
                ReviewModel.denyOffer(offerID, 3);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        info.setText("Tilbuddet er hermed afvist");

        date.clear();
        custName.clear();
        phone.clear();
        interest.clear();
        duration.clear();
        carName.clear();
        carPrice.clear();
        deposit.clear();
        credit.clear();
        loan_total.clear();
    }

    public void logud(ActionEvent event) throws IOException { logudButton(event); }
    public void createKnap(ActionEvent event) throws IOException { createButton(event); }
    public void offerKnap(ActionEvent event) throws IOException { offerButton(event); }
    public void inventoryKnap(ActionEvent event) throws IOException { inventoryButton(event); }
    public void statsKnap(ActionEvent event) throws IOException { statsButton(event); }
    public void customerKnap(ActionEvent event) throws IOException { customerButton(event); }
}



