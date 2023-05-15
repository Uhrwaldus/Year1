package beregner.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ReviewApplication extends Application {
    public static void main(String[] args) throws SQLException {
        //connect til database
        ReviewModel db = new ReviewModel();
        db.Connect();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeApplication.class.getResource("Review.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Review");
        stage.setScene(scene);
        stage.show();
    }
}
