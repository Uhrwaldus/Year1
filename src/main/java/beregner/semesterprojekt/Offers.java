package beregner.semesterprojekt;

import java.sql.Date;
// Ulrikke

public class Offers {
    private int id, interest, loan, deposit, duration, customerID, saleID, carID, carPrice, phone, monthlyPay;
    private Date date;
    private String name, lastname, email, address, city, zip, cpr, rating,credit;


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

    public String getName() {return name;}

    public String getLastname() {return lastname;}

    public String getEmail() {return email;}

    public String getAddress() {return address;}

    public String getCity() {return city;}

    public String getZip() {return zip;}

    public String getCpr() {return cpr;}

    public String getCredit() {return credit;}

    public int getCarPrice() {return carPrice;}

    public int getPhone() {return phone;}

    public int getMonthlyPay() {return monthlyPay;}

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
