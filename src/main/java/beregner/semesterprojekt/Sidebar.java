package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
// Jonas
public class Sidebar {

    public void logudButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/beregner/semesterprojekt/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void createButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/beregner/semesterprojekt/CreateOffer.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void offerButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/beregner/semesterprojekt/Offer.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void inventoryButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/beregner/semesterprojekt/Inventory.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void statsButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/beregner/semesterprojekt/Statistics.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    public void customerButton(ActionEvent event) throws IOException {
        URL url = new File("src/main/resources/beregner/semesterprojekt/Customer.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}

