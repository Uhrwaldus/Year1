package beregner.semesterprojekt;
// Ulrikke
public class CarInfo {
    private String model;
    private int price, stock;

    public CarInfo(String model, int price, int stock){
        this.model = model;
        this.price = price;
        this.stock = stock;
    }

    public String getModel() { return model; }

    public int getPrice() { return price; }

    public int getStock() { return stock; }
}
