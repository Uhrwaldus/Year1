package beregner.semesterprojekt;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testng.annotations.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.testng.AssertJUnit.assertEquals;


/* Jonas
    Det er værd at nævne, at denne testklasse fungerede som forventen 29/05 kl. 17:29 da jeg pushede mit commit.
    Morgenen efter, 30/05 kl. 10:03 gives der nu fejlkoden:
    SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    SLF4J: Defaulting to no-operation (NOP) logger implementation
    SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
*/

public class CreateOfferControllerTest extends ApplicationTest {

    private CreateOfferController controller;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateOffer.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        controller = loader.getController();
    }

    @Test
    public void testTextFieldValues() {
        // Sætter values
        clickOn(controller.customerBox).clickOn("Jonas");
        clickOn(controller.checkCPR);
        clickOn(controller.carBox).clickOn("Ferrari F40");
        clickOn(controller.depositInput).write("100000");
        clickOn(controller.durationInput).moveBy(10, 0);
        clickOn(controller.salesIDInput).write("1");

        //Tråden sover for at sikre at kreditvurderings-API'en har tid til at indsætte vurderingen
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }

        Platform.runLater(() -> {

            // Starter verifikationen
            controller.CreateOfferOnClick(null);

            // Verificerer at inputs er korrekte, samt udregningen
            assertEquals("Jonas", controller.firstnameInput.getText());
            assertEquals("Pedersen", controller.lastnameInput.getText());
            assertEquals("jonpe01@eamv.dk", controller.emailInput.getText());
            assertEquals("Sønderparken 26", controller.addressInput.getText());
            assertEquals("Holstebro", controller.cityInput.getText());
            assertEquals("7500", controller.zipInput.getText());
            assertEquals("1234567890", controller.cprInput.getText());
            assertEquals("6,59", controller.interestInput.getText());
            assertEquals("B", controller.creditInput.getText());
            assertEquals("100000", controller.depositInput.getText());
            assertEquals("3500000.0", controller.carPriceInput.getText());
            assertEquals("30622907", controller.phoneInput.getText());
            assertEquals("1", controller.salesIDInput.getText());
            assertEquals("1", controller.kundeIDinput.getText());
            assertEquals("1", controller.carIDinput.getText());
            assertEquals("62101,62", controller.result.getText());

        });
    }
}
