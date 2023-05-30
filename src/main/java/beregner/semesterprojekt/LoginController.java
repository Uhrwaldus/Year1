package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
// Jonas
public class LoginController implements Initializable {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    private LoginModel model;
    private Stage stage;

    public LoginController() {
        model = new LoginModel();
    }

    @FXML
    private void loginButtonClicked(ActionEvent event) throws IOException {
        //Tager bruger-input og validerer det i databasen ved brug af "validateUser"
        int userTypeID = model.validateUser(usernameField.getText(), passwordField.getText());

        //Hvis brugeren ikke findes, gives der ikke en userType, og der fejlmeldes
        if (userTypeID == -1) {
            errorLabel.setText("Forkert bruger ID eller password");
        } else {
            //Switch-case som sender programmet til den næste scene baseret på userType
            switch (userTypeID) {
                case 1: //Skift fxml-filerne ud med de korrekte :)
                    loadFXML("Home.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow(), event);
                    break;
                case 2:
                    loadFXML("Review.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow(), event);
                    break;
            }
        }
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void loadFXML(String fxmlName, Stage stage, ActionEvent event) throws IOException {
        //Loader den kommende scene
        FXMLLoader adminLoader = new FXMLLoader(getClass().getResource(fxmlName));
        Parent Root = adminLoader.load();
        Scene Scene = new Scene(Root);
        Stage Stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(Scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connecter til databasen gennem LoginModel
        LoginModel database = new LoginModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.model = database;
    }
}