package beregner.semesterprojekt;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest extends ApplicationTest {

    private LoginController loginController;
    private Stage primaryStage;

    @BeforeAll
    public static void setup() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LoginApplication.class);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        loginController = loader.getController();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Test
    public void testLoginButtonClickedWithValidUser() throws IOException {
        //Testen virker, men jeg har nada idé hvorfor den hvide boks kommer frem
        Platform.runLater(() -> {
            LoginController loginController = new LoginController();

            // Set the stage property
            Stage primaryStage = new Stage();
            loginController.setStage(primaryStage);

            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root;
            try {
                root = loader.load();
                Scene scene = new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();

                // Lookup the fields and enter valid credentials
                TextField usernameField = lookup("#usernameField").query();
                PasswordField passwordField = lookup("#passwordField").query();
                usernameField.setText("1");
                passwordField.setText("1234");

                // Invoke the button click
                Button loginButton = lookup("#loginButton").query();
                clickOn(loginButton);
                Scene currentScene = primaryStage.getScene();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    public void testLoginButtonClickedWithInvalidUser() {
        //Virker
        TextField usernameField = lookup("#usernameField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Label errorLabel = lookup("#errorLabel").query();

        // Enter invalid credentials
        usernameField.setText("1");
        passwordField.setText("123123");

        clickOn("#loginButton");

        assertEquals("Forkert bruger ID eller password", errorLabel.getText());
    }
}

