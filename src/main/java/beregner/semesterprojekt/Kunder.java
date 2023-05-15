package beregner.semesterprojekt;

public class Kunder {
    private int column1;
    private String column2;
    private String column3;
    private String column4;
    private int column5;
    private String column6;
    private String column7;
    private int column8;

    public Kunder(int column1, String column2, String column3, String column4, int column5, String column6, String column7, int column8){
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
        this.column4 = column4;
        this.column5 = column5;
        this.column6 = column6;
        this.column7 = column7;
        this.column8 = column8;
    }

    public int getColumn1(){
        return column1;
    }
    public String getColumn2(){
        return column2;
    }
    public String getColumn3(){
        return column3;
    }
    public String getColumn4(){
        return column4;
    }
    public int getColumn5(){
        return column5;
    }
    public String getColumn6(){
        return column6;
    }
    public String getColumn7(){
        return column7;
    }
    public int getColumn8(){
        return column8;
    }
}
