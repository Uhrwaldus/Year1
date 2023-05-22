package beregner.semesterprojekt;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateOfferController implements Initializable {
    private CreateOfferModel database;

    @FXML
    private TextField firstnameInput, lastnameInput, emailInput, addressInput, cityInput, zipInput, cprInput,
            interestInput, creditInput, depositInput, carNameInput, carPriceInput, phoneInput, salesIDInput;
    @FXML
    private Button createOfferOnClick;
    @FXML
    private ChoiceBox<String> customerBox;
    @FXML
    private ChoiceBox<String> carBox;

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

        customerBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                String[] customerData = CreateOfferModel.getCustomerData(newValue);
                firstnameInput.setText(customerData[0]);
                lastnameInput.setText(customerData[1]);
                emailInput.setText((customerData[2]));
                phoneInput.setText(customerData[3]);
                addressInput.setText(customerData[4]);
                cityInput.setText(customerData[5]);
                zipInput.setText(customerData[6]);
                cprInput.setText(customerData[7]);

                } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
