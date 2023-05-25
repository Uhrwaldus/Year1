package beregner.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StatView extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("Statistics.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Statistics");
        stage.setScene(scene);
        stage.show();
    }

}
