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

    public static void opretKunde(int column1, String column2, String column3, String column4, int column5, String column6, String column7, int column8) {
        try {
            PreparedStatement SQLopret = connection.prepareStatement(
                    "INSERT INTO customer (column1, column2, column3, column4, column5, column6, column7, column8) " +
                            "VALUES (?, ?, ?, ?, ?, ?)");
            SQLopret.setString(1, String.valueOf(column1));
            SQLopret.setString(2,column2);
            SQLopret.setString(3,column3);
            SQLopret.setString(4,column4);
            SQLopret.setString(5, String.valueOf(column5));
            SQLopret.setString(6,column6);
            SQLopret.setString(7,column7);
            SQLopret.setString(8, String.valueOf(column8));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}