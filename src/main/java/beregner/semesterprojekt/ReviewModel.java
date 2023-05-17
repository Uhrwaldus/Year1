package beregner.semesterprojekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewModel {
    private ReviewModel database;
    private static Connection connection;


    public boolean Connect() throws SQLException {

        String connectionString =
                "jdbc:sqlserver://localhost:1433;" +
                        "databaseName=FerrariDB;" +
                        "user=SA;" +
                        "password=Fisk1234;" +
                        "encrypt=true;" +
                        "trustServerCertificate=true;";

        connection = null;

        try {
            System.out.println("Connecting to database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to database");

            return true;
        }
        catch (SQLException e) {
            System.out.println("Could not connect to database!");
            System.out.println(e.getMessage());

            return false;
        }
    }
    public static void getOffer(ChoiceBox choiceBox) {
        ObservableList<Integer> setOffer = FXCollections.observableArrayList();
        try {
            String sql = "SELECT offer_ID FROM offer";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int offer_ID = rs.getInt("offer_ID");
                setOffer.add(offer_ID);
            }
            choiceBox.setItems(setOffer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void getInfo(ChoiceBox<Integer> choiceBox, TextField date, TextField interest, TextField credit,
                               TextField loan_total, TextField deposit, TextField duration) {
        int selectedId = choiceBox.getValue();
        String query = "SELECT date, interest, credit_rating, loan_total, deposit, duration FROM offer WHERE offer_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, selectedId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                date.setText(String.valueOf(resultSet.getDate("date")));
                interest.setText(String.valueOf(resultSet.getDouble("interest")));
                credit.setText(resultSet.getString("credit_rating"));
                loan_total.setText(String.valueOf(resultSet.getInt("loan_total")));
                deposit.setText(String.valueOf(resultSet.getInt("deposit")));
                duration.setText(String.valueOf(resultSet.getInt("duration")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
