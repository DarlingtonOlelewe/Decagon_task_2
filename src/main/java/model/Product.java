package model;

public class Product {
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int serial;

    public Product(String name, String category, double price, int quantity, int serial) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public int getSerial(){
        return serial;
    }


    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price + '\'' +
                ", quantity" + quantity + '\'' +
                ", Serial no" + serial + '\'' +
                '}';
    }
}
