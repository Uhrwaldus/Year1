import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        //connect til database
        DataAccess db = new DataAccess();
        db.Connect();
    }
}