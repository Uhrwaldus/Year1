package beregner.semesterprojekt;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
// Ulrikke

public class CustomerModel {
    private static Connection connection;
    private static ObservableList<Kunder> dataList = FXCollections.observableArrayList();

    public void Connect() throws SQLException {

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

        }
        catch (SQLException e) {
            System.out.println("Could not connect to database!");
            System.out.println(e.getMessage());

        }
    }

    public static void loadCustomers() throws SQLException {
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
            results = SQLkunder.executeQuery("SELECT * FROM customer");

            dataList.clear();

            while (results.next()) {
                Kunder data = new Kunder(
                        results.getInt("customer_ID"),
                        results.getString("firstname"),
                        results.getString("lastname"),
                        results.getString("email"),
                        results.getInt("phonenumber"),
                        results.getString("adress"),
                        results.getString("city"),
                        results.getInt("postcode"),
                        results.getString("cpr")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ObservableList<Kunder> getDataList(){
        return dataList;
    }

    public static void opretKunde() {
        // sæt værdierne fra Opret ind i databasen
        try {
            PreparedStatement SQLopret = connection.prepareStatement(
                    "INSERT INTO customer (firstname, lastname, email, phonenumber, adress, city, postcode, CPR) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            SQLopret.setString(1,Opret.getNavn());
            SQLopret.setString(2,Opret.getEfternavn());
            SQLopret.setString(3,Opret.getEmail());
            SQLopret.setInt(4,Opret.getTlfnr());
            SQLopret.setString(5,Opret.getAddresse());
            SQLopret.setString(6,Opret.getBy());
            SQLopret.setInt(7,Opret.getPostnr());
            SQLopret.setString(8,Opret.getCprnr());

            SQLopret.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}