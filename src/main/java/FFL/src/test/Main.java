package test;
import java.util.Random;

import com.ferrari.finances.dk.bank.InterestRate;
import com.ferrari.finances.dk.rki.CreditRator;

public class Main {
  private static Random rnd = new Random();

  public static void main( String[] args ) {
    for ( int i=0; i<3; i++ )
      System.out.println( InterestRate.i().todaysRate() );
    
    for ( int i=0; i < 5; i++ ) {
      String cpr = randomCprLike();

      // two rate-calls on the same CPR
      System.out.println( cpr + ": " + CreditRator.i().rate( cpr ) );
      System.out.println( cpr + ": " + CreditRator.i().rate( cpr ) );
    }
    
    // Rate-calls for the invalid, but working, CPR numbers for testing
    System.out.println( "0000000000" + ": " + CreditRator.i().rate( "0000000000" ) );
    System.out.println( "0000000001" + ": " + CreditRator.i().rate( "0000000001" ) );
    System.out.println( "0000000002" + ": " + CreditRator.i().rate( "0000000002" ) );
    System.out.println( "0000000003" + ": " + CreditRator.i().rate( "0000000003" ) );
    
    // throws exception (only nine digits)
    CreditRator.i().rate( "123456789" );
  }

  private static String randomCprLike() {
    StringBuilder sb = new StringBuilder();

    for ( int i=0; i < 10; i++ )
      sb.append( rnd.nextInt( 10 ) );

    return sb.toString();
  }
}
