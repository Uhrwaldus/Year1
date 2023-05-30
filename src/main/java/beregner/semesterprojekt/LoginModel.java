package beregner.semesterprojekt;

import java.sql.*;
// Jonas
public class LoginModel {
    private Connection connection;
    private LoginModel database;


    public boolean Connect() throws SQLException {

        String connectionString =
                "jdbc:sqlserver://localhost:1433;" +
                        "databaseName=FerrariDB;" +
                        "user=sa;" +
                        "password=Fisk1234;" +
                        "encrypt=true;" +
                        "trustServerCertificate=true;";
        try {
            System.out.println("Connecting to database...");

            connection = DriverManager.getConnection(connectionString);

            System.out.println("Connected to database");

            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to database!");
            System.out.println(e.getMessage());

            return false;
        }

    }
    public LoginModel() {
        try {
            Connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //Validerer brugeren baseret på deres inputs
    public int validateUser(String salesman_ID, String password) {
        //Sætter userType til -1, for at hjælpe fejlmelding
        int user_type_ID = -1;
        //Baseret på bruger inputs, hentes userType gennem SQL
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT user_type_ID FROM salesman WHERE salesman_ID = ? AND password = ?");
            ps.setString(1, salesman_ID);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user_type_ID = rs.getInt("user_type_ID");
            } return user_type_ID;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return -1;
    }
}