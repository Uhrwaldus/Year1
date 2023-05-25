package test;
import java.util.Random;

import com.ferrari.finances.dk.bank.InterestRate;
import FFL.src.com.ferrari.finances.dk.rki.CreditRator;

public class Main {
  private static Random rnd = new Random();

  public static void main( String[] args ) {

      String cpr = randomCprLike();

      System.out.println(cpr);

    System.out.println(cpr + CreditRator.i().rate(cpr));


  }

  private static String randomCprLike() {
    StringBuilder sb = new StringBuilder();

    for ( int i=0; i < 10; i++ )
      sb.append( rnd.nextInt( 10 ) );

    return sb.toString();
  }
}
