package beregner.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeApplication extends Application {
    /*
    public static void main(String[] args) throws SQLException {
        //connect til database
        DataAccess db = new DataAccess();
        db.Connect();
    }
    */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }
}
