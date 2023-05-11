package beregner.semesterprojekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class InventoryController implements Initializable {
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

    public void F40(MouseEvent event){
        String query = "SELECT * FROM car WHERE car_name = 'Ferrari F40'";
        try {
            conn = database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String model = rs.getString("car_name");
                int pris = rs.getInt("car_price");
                int stock = rs.getInt("stock");

                modelOutput.setText(model);
                prisOutput.setText(Integer.toString(pris));
                stockOutput.setText(Integer.toString(stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Spider(MouseEvent event){
        String query = "SELECT * FROM car WHERE car_name = 'Ferrari Spyder'";
        try {
            conn = database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String model = rs.getString("car_name");
                int pris = rs.getInt("car_price");
                int stock = rs.getInt("stock");

                modelOutput.setText(model);
                prisOutput.setText(Integer.toString(pris));
                stockOutput.setText(Integer.toString(stock));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
