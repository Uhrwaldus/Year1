package beregner.semesterprojekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ReviewApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Review.fxml"));
        primaryStage.setTitle("Review");
        primaryStage.setScene(new Scene(root, 800, 580));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
