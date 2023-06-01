package beregner.semesterprojekt;


import java.sql.*;
// Casper
public class InventoryModel {
    //Opretter Connection til Databasen med nedenstående metode.
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

    // Ulrikke
    public CarInfo getCarInfo(String carName){
        CarInfo carInfo = null;
        String query = "SELECT * FROM car WHERE car_name = ?";
        try {
            Connect();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, carName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String model = rs.getString("car_name");
                int price = rs.getInt("car_price");
                int stock = rs.getInt("stock");
                carInfo = new CarInfo(model, price, stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carInfo;
    }
}
