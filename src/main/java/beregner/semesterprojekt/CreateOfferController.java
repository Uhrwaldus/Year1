package beregner.semesterprojekt;


import FFL.src.com.ferrari.finances.dk.rki.CreditRator;
import FFL.src.com.ferrari.finances.dk.rki.Rating;
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
                //Sætter data basere på valg af bil i ChoiceBox
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
                //Sætter data baseret på valg af kunde i ChoiceBoxen
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
        //Kalder rentesats API'en for at hente dagens rentesats
        double rentesats = com.ferrari.finances.dk.bank.InterestRate.i().todaysRate();
        interestInput.setText(String.valueOf(rentesats));

        String cpr = cprInput.getText();
        // Kalder kreditvurderings API'en, for at generere kundes kreditvurdering
        Rating creditRating = FFL.src.com.ferrari.finances.dk.rki.CreditRator.i().rate(cpr);
        creditInput.setText(String.valueOf(creditRating));

    }
    public void CreateOfferOnClick(ActionEvent event) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        String interestText = interestInput.getText();
        String creditRatingText = creditInput.getText();
        String depositText = depositInput.getText();
        int duration = (int) durationInput.getValue();
        String salesIDText = salesIDInput.getText();
        String custIDText = kundeIDinput.getText();
        String carIDText = carIDinput.getText();
        String carPriceText = carPriceInput.getText();

        double creditAddition = 0.0;

        // Tilføj procenter baseret på kundes kreditvurdering, samt advarsel ved for lav kreditvurdering
        if (creditRatingText.equals("A")) {
            creditAddition = 0.01;
        } else if (creditRatingText.equals("B")) {
            creditAddition = 0.02;
        } else if (creditRatingText.equals("C")) {
            creditAddition = 0.03;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kunden er i RKI");
            alert.setHeaderText("Stop finansieringen af kunden med det samme");
            alert.showAndWait();
            return;
        }
        // Tilføj procenter baseret på lånets længde
        double durationAddition = 0.0;

        if(duration >= 36) {
            durationAddition = 0.01;
        }

        // Udregning af lån
        double deposit = Double.parseDouble(depositText);
        double price = Double.parseDouble(carPriceText);

        double total = (price - deposit) * ((com.ferrari.finances.dk.bank.InterestRate.i().todaysRate() / 100) +
                (1 + creditAddition + durationAddition)) / 60;

        // Set værdier fra databasen
        CreateOffer.setInterest(Double.parseDouble(interestText));
        CreateOffer.setCredit_rating(creditRatingText);
        CreateOffer.setDeposit(Integer.parseInt(depositText));
        CreateOffer.setDuration(duration);
        CreateOffer.setSalesID(Integer.parseInt(salesIDText));
        CreateOffer.setCustID(Integer.parseInt(custIDText));
        CreateOffer.setcarID(Integer.parseInt(carIDText));
        CreateOffer.setTotal(total);

        // Opdater TextFields med udregningens resultat
        result.setText(decimalFormat.format(total));

        CreateOfferModel.setOfferInfo();
    }

}
