package beregner.semesterprojekt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.*;
// Ulrikke og Casper

public class InventoryController extends Sidebar {
    private InventoryModel inventoryModel;

    public InventoryController(){
        inventoryModel = new InventoryModel();
    }

   @FXML
   private Label modelOutput, prisOutput, stockOutput;

    // Ulrikke
   public void updateCarInfo(String carName) {
       CarInfo carInfo = inventoryModel.getCarInfo(carName);
       if (carInfo != null) {
           modelOutput.setText(carInfo.getModel());
           prisOutput.setText(Integer.toString(carInfo.getPrice()));
           stockOutput.setText(Integer.toString(carInfo.getStock()));
       } else {
           modelOutput.setText("");
           prisOutput.setText("");
           stockOutput.setText("");
       }
   }

    // Casper
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

    // Jonas
    public void logud(ActionEvent event) throws IOException { logudButton(event); }
    public void createKnap(ActionEvent event) throws IOException { createButton(event); }
    public void offerKnap(ActionEvent event) throws IOException { offerButton(event); }
    public void inventoryKnap(ActionEvent event) throws IOException { inventoryButton(event); }
    public void statsKnap(ActionEvent event) throws IOException { statsButton(event); }
    public void customerKnap(ActionEvent event) throws IOException { customerButton(event); }

}
