import javafx.fxml.FXML;

import java.awt.*;
import java.awt.event.ActionEvent;

public class HomeController {
    int udbetaling, indkomst, bil, pris, periode;

    @FXML
    private TextField udbetalinginput;

    public void check(ActionEvent event ){
        udbetaling = Integer.parseInt(udbetalinginput.getText());
        System.out.println(udbetaling);
    }

}
