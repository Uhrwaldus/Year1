package beregner.semesterprojekt;

public class Kunder {
    private int ID;
    private String Navn;
    private String Efternavn;
    private String Email;
    private int Tlfnr;
    private String Addresse;
    private String By;
    private int Postnr;

    public Kunder(int column1, String column2, String column3, String column4, int column5, String column6, String column7, int column8){
        this.ID = column1;
        this.Navn = column2;
        this.Efternavn = column3;
        this.Email = column4;
        this.Tlfnr = column5;
        this.Addresse = column6;
        this.By = column7;
        this.Postnr = column8;
    }

    public int getID(){
        return ID;
    }
    public String getNavn(){
        return Navn;
    }
    public String getEfternavn(){
        return Efternavn;
    }
    public String getEmail(){
        return Email;
    }
    public int getTlfnr(){
        return Tlfnr;
    }
    public String getAddresse(){
        return Addresse;
    }
    public String getBy(){
        return By;
    }
    public int getPostnr(){
        return Postnr;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNavn(String navn) {
        Navn = navn;
    }

    public void setEfternavn(String efternavn) {
        Efternavn = efternavn;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setTlfnr(int tlfnr) {
        Tlfnr = tlfnr;
    }

    public void setAddresse(String addresse) {
        Addresse = addresse;
    }

    public void setBy(String by) {
        By = by;
    }

    public void setPostnr(int postnr) {
        Postnr = postnr;
    }
}
