package beregner.semesterprojekt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

// Casper
public class InventoryController extends Sidebar implements Initializable {
    private InventoryModel database;


    @Override //Bruger databasen fra Model Laget.
    public void initialize(URL url, ResourceBundle resourceBundle) {

        InventoryModel database = new InventoryModel();
        try {
            database.Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.database = database;
    }


   @FXML
   private Label modelOutput, prisOutput, stockOutput;

    private Connection conn; // initialize your database connection

     private void updateCarInfo(String carName) {
        String query = "SELECT * FROM car WHERE car_name = ?";
        try {
            conn = database.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, carName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String model = rs.getString("car_name");
                int price = rs.getInt("car_price");
                int stock = rs.getInt("stock");
                modelOutput.setText(model);
                prisOutput.setText(Integer.toString(price));
                stockOutput.setText(Integer.toString(stock));
            } else {
                modelOutput.setText("");
                prisOutput.setText("");
                stockOutput.setText("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void F40(MouseEvent event){
        updateCarInfo("Ferrari F40");
    }

    public void Spider(MouseEvent event){
        updateCarInfo("Ferrari Spyder");
    }

    public void Roma(MouseEvent event){
        updateCarInfo("Ferrari Roma");
    }

    public void Stradale(MouseEvent event){
        updateCarInfo("Ferrari SF90");
    }

    public void Portofino(MouseEvent event){
        updateCarInfo("Ferrari Portofino");
    }

    public void logud(ActionEvent event) throws IOException { logudButton(event); }
    public void createKnap(ActionEvent event) throws IOException { createButton(event); }
    public void offerKnap(ActionEvent event) throws IOException { offerButton(event); }
    public void inventoryKnap(ActionEvent event) throws IOException { inventoryButton(event); }
    public void statsKnap(ActionEvent event) throws IOException { statsButton(event); }
    public void customerKnap(ActionEvent event) throws IOException { customerButton(event); }

}
