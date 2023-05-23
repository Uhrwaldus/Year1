package beregner.semesterprojekt;

import java.sql.Date;

public class CreateOffer {

    private static double interest;
    private static String credit_rating;
    private static int loan_total;
    private static int deposit;
    private static int duration;
    private static int salesID;
    private static Date date;

    public static void setInterest(double Interest) {interest = Interest;}
    public static void setCredit_rating(String Credit_Rating) {credit_rating = Credit_Rating;}
    public static void setLoan_total(int Loan_Total) {loan_total = Loan_Total;}
    public static void setDeposit(int Deposit) {deposit = Deposit;}
    public static void setDuration(int Duration) {duration = Duration;}
    public static void setSalesID(int SalesmanID) {salesID = SalesmanID;}
    public static void setDate(Date ChosenDate) {date = ChosenDate;}

    public static double getInterest() {return interest;}
    public static String getCredit_rating() {return credit_rating;}
    public static int getLoan_total() {return loan_total;}
    public static int getDeposit() {return deposit;}
    public static int getDuration() {return duration;}
    public static int getSalesID() {return salesID;}
    public static Date getDate() {return date;}


}
