package beregner.semesterprojekt;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class OfferPopup {
    private Stage stage;

    public void display(Offers offer) {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Offer Details");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("offer_popup.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);

            ExportOfferController controller = loader.getController();
            controller.loadData(offer);

        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.showAndWait();
    }
}
