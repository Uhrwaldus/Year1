

import com.ferrari.finances.dk.bank.InterestRate;

import java.sql.SQLException;

public class Main {
    //public static void main(String[] args) throws SQLException {
        //connect til database
      //  DataAccess db = new DataAccess();
       // db.Connect();

    public static void main( String[] args ) {
        for ( int i=0; i<3; i++ )
            System.out.println( InterestRate.i().todaysRate() );
    }
}