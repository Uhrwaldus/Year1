package beregner.semesterprojekt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatModel {
    private Connection connection;

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
            System.out.println("Connecting to the database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to the database");

            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to the database!");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public List<String> retrieveSellersFromDatabase() throws SQLException {
        List<String> sellers = new ArrayList<>();

        String query = "SELECT firstname FROM salesman";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            String sellerName = resultSet.getString("firstname");
            sellers.add(sellerName);
        }

        resultSet.close();
        statement.close();

        return sellers;
    }

    public int retrieveNumberOfCarsSold(String sellerName) throws SQLException {
        String query = "SELECT number_of_cars_sold " +
                "FROM salesman " +
                "WHERE firstname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, sellerName);

        ResultSet resultSet = statement.executeQuery();
        int carsSold = 0;
        if (resultSet.next()) {
            carsSold = resultSet.getInt("number_of_cars_sold");
        }

        resultSet.close();
        statement.close();

        return carsSold;
    }

    public int retrieveNumberOfLoansGiven(String sellerName) throws SQLException {
        String query = "SELECT number_of_loans_given " +
                "FROM salesman " +
                "WHERE firstname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, sellerName);

        ResultSet resultSet = statement.executeQuery();
        int loansGiven = 0;
        if (resultSet.next()) {
            loansGiven = resultSet.getInt("number_of_loans_given");
        }

        resultSet.close();
        statement.close();

        return loansGiven;
    }


    private void updateNumberOfCarsSold(String sellerName, int carsSold) throws SQLException {
        String query = "UPDATE salesman " +
                "SET number_of_cars_sold = ? " +
                "WHERE firstname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, carsSold);
        statement.setString(2, sellerName);

        statement.executeUpdate();
        statement.close();
    }

    private void updateNumberOfLoansGiven(String sellerName, int loansGiven) throws SQLException {
        String query = "UPDATE salesman " +
                "SET number_of_loans_given = ? " +
                "WHERE firstname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, loansGiven);
        statement.setString(2, sellerName);

        statement.executeUpdate();
        statement.close();
    }
}
