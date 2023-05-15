package beregner.semesterprojekt;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
        Statement SQLkunder = null;
        ResultSet results = null;
        try {
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
                        results.getString("address"),
                        results.getString("city"),
                        results.getInt("postcode")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Luk af for ressourcerne efter data er hentet
            if (results != null) {
                try {
                    results.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (SQLkunder != null) {
                try {
                    SQLkunder.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static ObservableList<Kunder> getDataList(){
        return dataList;
    }
}

