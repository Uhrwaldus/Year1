package beregner.semesterprojekt;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
    private TextField carPriceInput;
    @FXML
    private TextField phoneInput;
    @FXML
    private TextField salesIDInput;
    @FXML
    private TextField kundeIDinput;
    @FXML
    private TextField carIDinput;
    @FXML
    private TextField result;
    @FXML
    private Button createOfferOnClick;
    @FXML
    private Slider durationInput;
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
                double price = CreateOfferModel.getCarPrice(newValue);
                carPriceInput.setText(String.valueOf(price));
                int id = CreateOfferModel.getCarID(newValue);
                carIDinput.setText(String.valueOf(id));

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
                kundeIDinput.setText(customerData[0]);
                firstnameInput.setText(customerData[1]);
                lastnameInput.setText(customerData[2]);
                emailInput.setText((customerData[3]));
                phoneInput.setText(customerData[4]);
                addressInput.setText(customerData[5]);
                cityInput.setText(customerData[6]);
                zipInput.setText(customerData[7]);
                cprInput.setText(customerData[8]);

                } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void InsertDataFromCPR(ActionEvent event) {
        double tester = com.ferrari.finances.dk.bank.InterestRate.i().todaysRate();
        interestInput.setText(String.valueOf(tester));

    }
    public void CreateOfferOnClick(ActionEvent event) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        // Remove non-numeric characters from input strings
        String interestText = interestInput.getText();
        String creditRatingText = creditInput.getText();
        String depositText = depositInput.getText();
        int duration = (int) durationInput.getValue();  // Access the value directly as an int
        String salesIDText = salesIDInput.getText();
        String custIDText = kundeIDinput.getText();
        String carIDText = carIDinput.getText();
        String carPriceText = carPriceInput.getText();

        // Set properties of CreateOffer object
        CreateOffer.setInterest(Double.parseDouble(interestText));
        CreateOffer.setCredit_rating(creditRatingText);
        CreateOffer.setDeposit(Integer.parseInt(depositText));
        CreateOffer.setDuration(duration);
        CreateOffer.setSalesID(Integer.parseInt(salesIDText));
        CreateOffer.setCustID(Integer.parseInt(custIDText));
        CreateOffer.setcarID(Integer.parseInt(carIDText));

        // Perform calculations
        double deposit = Double.parseDouble(depositText);
        double price = Double.parseDouble(carPriceText);
        double total = ((price - deposit) / duration *
                (com.ferrari.finances.dk.bank.InterestRate.i().todaysRate() / 100 + 1));

        // Set total property of CreateOffer object
        CreateOffer.setTotal(total);

        // Update result text component
        result.setText(decimalFormat.format(total));

        CreateOfferModel.setOfferInfo();
    }

}
