package beregner.semesterprojekt;

public class CreateOffer {

    private static double interest;
    private static String credit_rating;
    private static int deposit;
    private static int duration;
    private static int salesID;
    private static int custID;
    private static int carID;
    private static double total;

    public static void setInterest(double Interest) {interest = Interest;}
    public static void setCredit_rating(String Credit_Rating) {credit_rating = Credit_Rating;}
    public static void setDeposit(int Deposit) {deposit = Deposit;}
    public static void setDuration(int Duration) {duration = Duration;}
    public static void setSalesID(int SalesmanID) {salesID = SalesmanID;}
    public static void setCustID(int CustID) {custID = CustID;}
    public static void setcarID(int CarID) {carID = CarID;}
    public static void setTotal(double Total) {total = Total;}


    public static double getInterest() {return interest;}
    public static String getCredit_rating() {return credit_rating;}
    public static int getDeposit() {return deposit;}
    public static int getDuration() {return duration;}
    public static int getSalesID() {return salesID;}
    public static int getCustID() {return custID;}
    public static int getCarID() {return carID;}
    public static double getTotal() {return total;}



}
