package beregner.semesterprojekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class CreateOfferModel {
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

            connection = getConnection(connectionString);

            System.out.println("Connected to database");

            return true;
        }
        catch (SQLException e) {
            System.out.println("Could not connect to database!");
            System.out.println(e.getMessage());

            return false;
        }
    }
    public static void createNewOffer(String firstname, String lastname, String email, String address,
                                         String city, int postcode, int cpr, int phonenumber) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = connection;
            String sql = "INSERT INTO customer (firstname, lastname, email, address, city, postcode, cpr, phonenumber)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, firstname);
            stmt.setString(2, lastname);
            stmt.setString(3, email);
            stmt.setString(4, address);
            stmt.setString(5, city);
            stmt.setInt(6, postcode);
            stmt.setInt(7, cpr);
            stmt.setInt(8, phonenumber);

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void getCars(ChoiceBox choiceBox) throws SQLException {
        // Opretter en Observable list med String objects, henter bilerne fra databasen og sætter dem ind i array
        ObservableList<String> names = FXCollections.observableArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT car_name FROM car");
            while (rs.next()) {
                String name = rs.getString("car_name");
                names.add(name);
            }
            // sætter items i choicebox til navne fra arrayet
            choiceBox.setItems(names);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static double getPrice(String bil) throws SQLException {
        // Henter prisen fra det tilhørende navn på bilen i databasen
        try {
            PreparedStatement SQLpris = connection.prepareStatement("SELECT car_price FROM car WHERE car_name = ?");
            SQLpris.setString(1, bil);
            ResultSet rs = SQLpris.executeQuery();

            if (rs.next()){
                return rs.getDouble("car_price");
            } else {
                throw new SQLException("Der er ingen pris på " + bil);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getCustomer(ChoiceBox choiceBox) {
        ObservableList<String> customers = FXCollections.observableArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CONCAT(firstname, ' ', lastname) AS full_name FROM customer");
            while (rs.next()) {
                String customer = rs.getString("full_name");
                customers.add(customer);
            }
            // sætter items i choicebox til navne fra arrayet
            choiceBox.setItems(customers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
