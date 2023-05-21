package beregner.semesterprojekt;

public class Kunder {
    private int ID;
    private static String Navn;
    private static String Efternavn;
    private static String Email;
    private static int Tlfnr;
    private static String Addresse;
    private static String By;
    private static int Postnr;

    public Kunder(int column1, String column2, String column3, String column4, int column5, String column6, String column7, int column8){
        this.ID = column1;
        Navn = column2;
        Efternavn = column3;
        Email = column4;
        Tlfnr = column5;
        Addresse = column6;
        By = column7;
        Postnr = column8;
    }

    public int getID(){
        return ID;
    }
    public static String getNavn(){
        return Navn;
    }
    public static String getEfternavn(){
        return Efternavn;
    }
    public static String getEmail(){
        return Email;
    }
    public static int getTlfnr(){
        return Tlfnr;
    }
    public static String getAddresse(){
        return Addresse;
    }
    public static String getBy(){
        return By;
    }
    public static int getPostnr(){
        return Postnr;
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
