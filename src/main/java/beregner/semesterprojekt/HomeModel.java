package beregner.semesterprojekt;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
// Alle
public class HomeModel {
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

    public void getBiler(ChoiceBox choiceBox) throws SQLException {
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
}

