package beregner.semesterprojekt;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CreateOfferController implements Initializable {
    private CreateOfferModel database;

    @FXML
    private TextField firstnameInput;
    @FXML
    private TextField lastnameInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField addressInput;
    @FXML
    private TextField cityInput;
    @FXML
    private TextField zipInput;
    @FXML
    private TextField cprInput;
    @FXML
    private TextField interestInput;
    @FXML
    private TextField creditInput;
    @FXML
    private TextField depositInput;
    @FXML
    private TextField carNameInput;
    @FXML
    private TextField carPriceInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField salesIDInput;
    @FXML
    private Button createOfferOnClick;
    @FXML
    private Slider durationInput;
    @FXML
    private DatePicker date;
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
    public void CreateOfferOnClick(ActionEvent event) {
        CreateOffer.setInterest(Double.parseDouble(interestInput.getText()));
        CreateOffer.setCredit_rating(creditInput.getText());
        CreateOffer.setDeposit(Integer.parseInt(depositInput.getText()));
        CreateOffer.setDuration((int) durationInput.getValue());
        CreateOffer.setSalesID(Integer.parseInt(salesIDInput.getText()));

        CreateOfferModel.setOfferInfo();

    }
}
