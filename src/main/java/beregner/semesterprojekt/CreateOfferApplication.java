package beregner.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateOfferApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("CreateOffer.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Opret Tilbud");
        stage.setScene(scene);
        stage.show();
    }
}
