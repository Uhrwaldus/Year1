package beregner.semesterprojekt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
// Ulrikke
public class ExportOfferController {
    @FXML
    private TextField
            nameField,lastnameField,emailField,addressField,cityField,
            zipField,cprField,interestField,creditField,depositField,
            carPriceField,phoneField,saleIDField,customerIDField,carIDField,
            durationField,monthlyPayField;
    @FXML
    private Button exportButton;


    public void loadData(Offers offer) {
        // sætter data ind fra Offers i de rigtige felter
        nameField.setText(offer.getName());
        lastnameField.setText(offer.getLastname());
        emailField.setText(offer.getEmail());
        addressField.setText(offer.getAddress());
        cityField.setText(offer.getCity());
        zipField.setText(offer.getZip());
        cprField.setText(offer.getCpr());
        interestField.setText(String.valueOf(offer.getInterest()));
        creditField.setText(offer.getCredit());
        depositField.setText(String.valueOf(offer.getDeposit()));
        carPriceField.setText(String.valueOf(offer.getCarPrice()));
        phoneField.setText(String.valueOf(offer.getPhone()));
        saleIDField.setText(String.valueOf(offer.getSaleID()));
        customerIDField.setText(String.valueOf(offer.getCustomerID()));
        carIDField.setText(String.valueOf(offer.getCarID()));
        durationField.setText(String.valueOf(offer.getDuration()));
        monthlyPayField.setText(String.valueOf(offer.getMonthlyPay()));
    }

    // CSV export metode
}
