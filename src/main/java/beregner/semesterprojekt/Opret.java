package beregner.semesterprojekt;

public class Opret {
    private static String Navn;
    private static String Efternavn;
    private static int Tlfnr;
    private static String Email;
    private static String Addresse;
    private static String By;
    private static int Postnr;
    private static String Cprnr;

    public void setNavn(String navn) {
        Navn = navn;
    }

    public void setEfternavn(String efternavn) {
        Efternavn = efternavn;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setTlfnr(int tlfnr) {Tlfnr = tlfnr;}

    public void setAddresse(String addresse) {
        Addresse = addresse;
    }

    public void setBy(String by) {
        By = by;
    }

    public void setPostnr(int postnr) {Postnr = postnr;}
    public void setCprnr(String cprnr) {Cprnr = cprnr;}

    public static String getNavn() {return Navn;}

    public static String getEfternavn() {return Efternavn;}

    public static int getTlfnr() {return Tlfnr;}

    public static String getEmail() {return Email;}

    public static String getAddresse() {return Addresse;}

    public static String getBy() {return By;}

    public static int getPostnr() {return Postnr;}

    public static String getCprnr() {return Cprnr;}
}
