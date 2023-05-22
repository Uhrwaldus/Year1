package beregner.semesterprojekt;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateOfferController implements Initializable {
    private CreateOfferModel database;

    String firstname, lastname, email, address, city, carName, credit;
    int postcode, cpr, phonenumber, deposit, salesID, interest;
    double carPrice;

    @FXML
    private TextField firstnameInput, lastnameInput, emailInput, addressInput, cityInput, zipInput, cprInput,
            interestInput, creditInput, depositInput, carNameInput, carPriceInput, phoneInput, salesIDInput;
    @FXML
    private Button createOfferOnClick;
    @FXML
    private ChoiceBox customerBox, carBox;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CreateOfferModel database = new CreateOfferModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.database = database;

        try {
            database.getCars(carBox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        carBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                double carPrice = CreateOfferModel.getPrice((String) newValue);
                carPriceInput.setText(String.valueOf(carPrice));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
        try {
            database.getCustomer(customerBox);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void getInfo(ActionEvent Actionevent) {
        interest = Integer.parseInt(interestInput.getText());
        credit = creditInput.getText();
        email = emailInput.getText();
        deposit = Integer.parseInt(depositInput.getText());
        salesID = Integer.parseInt(salesIDInput.getText());
        carName = carNameInput.getText();
        carPrice = Integer.parseInt(carPriceInput.getText());

        try {
            CreateOfferModel.createNewOffer(firstname, lastname, email, address, city, postcode, cpr, phonenumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
