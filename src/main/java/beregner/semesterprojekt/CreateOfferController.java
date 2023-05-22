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
                String firstname = CreateOfferModel.getCustomerData(newValue);
                firstnameInput.setText(CreateOfferModel.getCustomerData(firstname));
                lastnameInput.setText(CreateOfferModel.;

                /*String email = CreateOfferModel.getCustomerData(newValue);
                emailInput.setText(email);
                int phonenumber = Integer.parseInt(CreateOfferModel.getCustomerData(newValue));
                phoneInput.setText(String.valueOf(phonenumber));
                String address = CreateOfferModel.getCustomerData(newValue);
                addressInput.setText(address);
                String city = CreateOfferModel.getCustomerData(newValue); */
                } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
