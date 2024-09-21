package item;

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    public String getId() {
        return null;
    }

    public String getBrand() {
        return null;
    }

    @Override
    public String toString() {
        return "Item: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }



}

