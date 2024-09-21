package cosmeticproducts;

import item.Item;


public class CosmeticProducts extends Item {
    public static final String CATEGORY_ID = "PC";

    public CosmeticProducts(String name, double price, int quantity) {
        super(name, price, quantity);
    }
}
