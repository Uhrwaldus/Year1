package beregner.semesterprojekt;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
// Ulrikke

public class OfferModel {
    private static Connection connection;
    private static ObservableList<Offers> dataList = FXCollections.observableArrayList();

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

    public static void loadOffers() throws SQLException {
        Statement SQLkunder;
        ResultSet results;
        try {
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                    "databaseName=FerrariDB;" +
                    "user=SA;" +
                    "password=Fisk1234;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;");
            // hent kunde data fra databasen
            SQLkunder = connection.createStatement();
            results = SQLkunder.executeQuery("SELECT * FROM offer");

            dataList.clear();

            while (results.next()) {
                Offers data = new Offers(
                        results.getInt("offer_ID"),
                        results.getDate("date"),
                        results.getInt("interest"),
                        results.getString("credit_rating"),
                        results.getInt("loan_total"),
                        results.getInt("deposit"),
                        results.getInt("duration"),
                        results.getInt("customer_ID"),
                        results.getInt("salesman_ID"),
                        results.getInt("car_ID")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Offers> getDataList(){
        return dataList;
    }

    public OfferModel getOfferData(Offers selectedItem) throws SQLException {
        String getData = "SELECT * FROM offer WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(getData)) {
            statement.setInt(1,selectedItem.getId());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Date date = rs.getDate("datetime");
                int interest = rs.getInt("interest");
                String credit = rs.getString("credit_rating");
                int loan = rs.getInt("loan_total");
                int deposit = rs.getInt("deposit");
                int duration = rs.getInt("duration");
                int customerID = rs.getInt("customer_ID");
                int saleID = rs.getInt("salesman_ID");
                int carID = rs.getInt("car_ID");

                return new OfferModel();
            }
        }
        return null;
    }
}
