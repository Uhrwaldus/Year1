package beregner.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class OfferApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OfferApplication.class.getResource("Offer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    static void openPopup(Offers selectedItem){
        try {
            FXMLLoader loader = new FXMLLoader(OfferApplication.class.getResource("beregner/semesterprojekt/ExportOffer.fxml"));
            Parent root = loader.load();

            ExportOfferController controller = loader.getController();

            controller.setSelectedItem(selectedItem);

            OfferModel database = new OfferModel();
            Offers offer = database.getOfferData(selectedItem);
            controller.loadData(offer);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
