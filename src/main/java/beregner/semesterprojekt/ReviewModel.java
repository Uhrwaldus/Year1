package beregner.semesterprojekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
// Jonas
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
    //Henter alle tilbud gennem offer_ID, og sætter dem i choicebox.
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
    //Henter infomation fra databasen gennem SQL-sætning, og indsætter data i textfields
    public static void getOfferInfo(ChoiceBox<Integer> choiceBox, TextField date, TextField interest, TextField credit,
                               TextField loan_total, TextField deposit, TextField duration,
                                    TextField carName, TextField carPrice, TextField phone, TextField custName) {
        int selectedId = choiceBox.getValue();
        String query = "SELECT offer.date, offer.interest, offer.credit_rating, offer.loan_total, offer.deposit, " +
                "offer.duration, CONCAT(customer.firstname, ' ', customer.lastname) AS full_name, customer.phonenumber, car.car_name, " +
                "car.car_price FROM offer " +
                "JOIN customer ON offer.customer_ID = customer.customer_ID " +
                "JOIN car ON offer.car_ID = car.car_ID " +
                "WHERE offer.offer_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, selectedId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) { //Indsætter data i textfields
                date.setText(String.valueOf(resultSet.getDate("date")));
                interest.setText(String.valueOf(resultSet.getDouble("interest")));
                credit.setText(resultSet.getString("credit_rating"));
                loan_total.setText(String.valueOf(resultSet.getInt("loan_total")));
                deposit.setText(String.valueOf(resultSet.getInt("deposit")));
                duration.setText(String.valueOf(resultSet.getInt("duration")));
                carName.setText(resultSet.getString("car_name"));
                carPrice.setText(String.valueOf(resultSet.getInt("car_price")));
                phone.setText(String.valueOf(resultSet.getInt("phonenumber")));
                custName.setText(resultSet.getString("full_name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void acceptOffer(int offerID, int status_ID) throws SQLException {
        //Sætter status_ID til godkendt
        String query = "UPDATE offer SET status_ID = ? WHERE offer_ID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, 2);
            stmt.setInt(2, offerID);
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static void denyOffer(int offerID, int status_ID) throws  SQLException {
        //Sætter status_ID til afvist
        String query = "UPDATE offer SET status_ID = ? WHERE offer_ID = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, 3);
            stmt.setInt(2, offerID);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
