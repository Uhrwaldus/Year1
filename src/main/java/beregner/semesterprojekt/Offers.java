package beregner.semesterprojekt;

import java.sql.Date;
// Ulrikke

public class Offers {
    private int id;
    private Date date;
    private int interest;
    private String rating;
    private int loan;
    private int deposit;
    private int duration;
    private int customerID;
    private int saleID;
    private int carID;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getInterest() {
        return interest;
    }

    public String getRating() {
        return rating;
    }

    public int getLoan() {
        return loan;
    }

    public int getDeposit() {
        return deposit;
    }

    public int getDuration() {
        return duration;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getSaleID() {
        return saleID;
    }

    public int getCarID() {
        return carID;
    }

    public Offers(int column1, Date column2, int column3, String column4, int column5, int column6, int column7, int column8, int column9, int column10){
        this.id = column1;
        this.date = column2;
        this.interest = column3;
        this.rating = column4;
        this.loan = column5;
        this.deposit = column6;
        this.duration = column7;
        this.customerID = column8;
        this.saleID = column9;
        this.carID = column10;
    }
}
