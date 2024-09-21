package electronicdevices;

public class HairStraightener extends ElectronicDevices {
    private String brand;
    private int arrivalOrder;
    private String id;

    public HairStraightener(String name, double price,String brand, int quantity, int arrivalOrder) {
        super(name, price, quantity);
        this.brand = brand;
        this.arrivalOrder = arrivalOrder;
        this.id = generateId();
    }

    private String generateId() {
        return "ab-" + brand + "-" + ElectronicDevices.CATEGORY_ID + "-" + String.format("%03d", arrivalOrder);
    }

    @Override
    public String getId() {
        return id; // Devuelve el ID específico de Shampoo.
    }

    public String getBrand(){
        return brand;
    }

    @Override
    public String toString() {
        return super.toString() + ", Brand: " + brand + ", ID: " + id;
    }
}
