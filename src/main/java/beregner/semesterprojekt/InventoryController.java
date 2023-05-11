package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {
    private InventoryModel database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        InventoryModel database = new InventoryModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.database = database;
    }
    int Stock;
    double pris;
    String model;




    @FXML
    private Label modelOutput, prisOutput, stockOutput;

    @FXML
    private void F40(MouseEvent event) {
        stockOutput.setText("1");
        modelOutput.setText("F40");
        prisOutput.setText("3.000.000.000");
    }
    @FXML
    private void Spider(MouseEvent event) {
        stockOutput.setText("2");
        modelOutput.setText("Spider");
        prisOutput.setText("3.500.000.000");
    }





}
