package beregner.semesterprojekt;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {
    private InventoryView app;
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




}
