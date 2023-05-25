package beregner.semesterprojekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class CreateOfferModel {
    private static Connection connection;
    private static CreateOfferModel database;
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
    public static double getCarPrice(String bil) throws SQLException {
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

    public static int getCarID(String id) throws SQLException {
        try {
            //Henter carID baseret på bilens navn
            PreparedStatement SQLid = connection.prepareStatement("SELECT car_ID FROM car WHERE car_name = ?");
            SQLid.setString(1, id);
            ResultSet rs = SQLid.executeQuery();

            if (rs.next()) {
                return rs.getInt("car_ID");
            } else {
                throw new SQLException("Der skete en fejl, prøv venligt igen");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public void getCustomer(ChoiceBox choiceBox) throws SQLException {
        ObservableList<String> customers = FXCollections.observableArrayList();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT firstname, lastname, email, phonenumber, address, city, " +
                    "postcode, CPR FROM customer");
            while (rs.next()) {
                String firstname = rs.getString("firstname");

                customers.add(firstname);
            }
            // sætter items i choicebox til navne fra arrayet
            choiceBox.setItems(customers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String[] getCustomerData(String name) {
        try {
            PreparedStatement SQLCustInfo = connection.prepareStatement("SELECT customer_ID, firstname, lastname, email, phonenumber," +
                    " address, city, postcode, CPR FROM customer WHERE firstname = ?");
            SQLCustInfo.setString(1, name);
            ResultSet resultSet = SQLCustInfo.executeQuery();

            if (resultSet.next()) {
                int customer_ID = resultSet.getInt("customer_ID");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                int phonenumber = resultSet.getInt("phonenumber");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                int postcode = resultSet.getInt("postcode");
                int cpr = resultSet.getInt("CPR");
                return new String[]{String.valueOf(customer_ID), firstname, lastname, email, String.valueOf(phonenumber), address, city,
                        String.valueOf(postcode), String.valueOf(cpr)};
            } else {
                throw new SQLException("Customer not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setOfferInfo() {
        try {
            PreparedStatement SQLoffer = connection.prepareStatement("INSERT INTO offer ( interest, " +
                    "credit_rating, deposit, duration, salesman_ID, customer_ID, car_ID, loan_total, date) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, GETDATE())"); //GETDATE indsætter nuværende dato i databasen

            SQLoffer.setDouble(1, CreateOffer.getInterest());
            SQLoffer.setString(2, CreateOffer.getCredit_rating());
            SQLoffer.setInt(3, CreateOffer.getDeposit());
            SQLoffer.setDouble(4, CreateOffer.getDuration());
            SQLoffer.setInt(5, CreateOffer.getSalesID());
            SQLoffer.setInt(6, CreateOffer.getCustID());
            SQLoffer.setInt(7, CreateOffer.getCarID());
            SQLoffer.setDouble(8, CreateOffer.getTotal());


            SQLoffer.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
