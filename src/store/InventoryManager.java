package store;

import item.Item;
import cosmeticproducts.Shampoo;
import cosmeticproducts.MoisturizingCream;
import cosmeticproducts.StylingWax;
import electronicdevices.HairDryer;
import electronicdevices.HairStraightener;


import java.util.*;


public class InventoryManager {

    // DECLARAMOS LA LISTA Y LAS COLAS
    private List<Item> itemList = new ArrayList<>() ;
    private Queue<Item> queue1 = new LinkedList<>();
    private Queue<Item> queue2 = new LinkedList<>();
    private Queue<Item> queue3 = new LinkedList<>();
    private Queue<Item> queue4 = new LinkedList<>();
    private Queue<Item> queue5 = new LinkedList<>();


    //LLAMARA NUESTRAS FUNCIONES EN EL ORDEN DESEADO
    public InventoryManager() {

//        itemList = new ArrayList<>(); //INICIALIZAMOS ARRAY VACIO , NECESITA CONSTRUCTOR ESPECIFICO , MAS NO ARRIBA COMO LAS COLAS

        generateRandomItemsWithTotalValue();
        transferItemsToQueues();
    }



    // Generar 1,000,000 artículos aleatorios
    private void generateRandomItemsWithTotalValue() {
        // Definición de los artículos, marcas y sus precios
        Map<String, Map<String, Integer>> itemPrices = new HashMap<>();

        // Agregar diferentes marcas y precios para Shampoo
        Map<String, Integer> shampooPrices = new HashMap<>();
        shampooPrices.put("Loreal", 4000);
        shampooPrices.put("vitale", 4500);
        shampooPrices.put("romero", 5000);
        itemPrices.put("Shampoo", shampooPrices);

        // Agregar diferentes marcas y precios para Moisturizing Cream
        Map<String, Integer> creamPrices = new HashMap<>();
        creamPrices.put("BrandA", 30000);
        creamPrices.put("BrandB", 32000);
        creamPrices.put("BrandC", 35000);
        itemPrices.put("Moisturizing Cream", creamPrices);

        // Agregar diferentes marcas y precios para Styling Wax
        Map<String, Integer> waxPrices = new HashMap<>();
        waxPrices.put("BrandA", 10000);
        waxPrices.put("BrandB", 11000);
        waxPrices.put("BrandC", 12000);
        itemPrices.put("Styling Wax", waxPrices);

        // Agregar diferentes marcas y precios para Hair Dryer
        Map<String, Integer> dryerPrices = new HashMap<>();
        dryerPrices.put("BrandA", 80000);
        dryerPrices.put("BrandB", 85000);
        dryerPrices.put("BrandC", 90000);
        itemPrices.put("Hair Dryer", dryerPrices);

        // Agregar diferentes marcas y precios para Hair Straightener
        Map<String, Integer> straightenerPrices = new HashMap<>();
        straightenerPrices.put("BrandA", 60000);
        straightenerPrices.put("BrandB", 65000);
        straightenerPrices.put("BrandC", 70000);
        itemPrices.put("Hair Straightener", straightenerPrices);

        // Total a alcanzar
        int totalValue = 1_000_000;
        Random random = new Random();

        while (totalValue > 0) {
            // Seleccionar un tipo de artículo aleatorio
            List<String> items = new ArrayList<>(itemPrices.keySet());
            String randomItem = items.get(random.nextInt(items.size()));

            // Seleccionar una marca aleatoria para el artículo
            Map<String, Integer> brandPrices = itemPrices.get(randomItem);
            List<String> brands = new ArrayList<>(brandPrices.keySet());
            String randomBrand = brands.get(random.nextInt(brands.size()));
            int price = brandPrices.get(randomBrand);

            // Definir una cantidad aleatoria
            int quantity = random.nextInt(10) + 1; // Cantidad entre 1 y 10

            // Calcular el costo total para esa cantidad
            int itemCost = quantity * price;

            // Verificar si el costo total excede el presupuesto restante
            if (itemCost <= totalValue) {
                // Agregar el artículo a la lista
                switch (randomItem) {
                    case "Shampoo":
                        itemList.add(new Shampoo(randomItem, price, randomBrand, quantity, itemList.size()));
                        break;
                    case "Hair Dryer":
                        itemList.add(new HairDryer(randomItem, price, randomBrand, quantity, itemList.size()));
                        break;
                    case "Moisturizing Cream":
                        itemList.add(new MoisturizingCream(randomItem, price, randomBrand, quantity, itemList.size()));
                        break;
                    case "Styling Wax":
                        itemList.add(new StylingWax(randomItem, price, randomBrand, quantity, itemList.size()));
                        break;
                    case "Hair Straightener":
                        itemList.add(new HairStraightener(randomItem, price, randomBrand, quantity, itemList.size()));
                        break;
                }
                // Restar del total
                totalValue -= itemCost;
            }
        }

        // Imprimir total restante para verificar
        if (totalValue < 0) {
            System.out.println("Se excedió el presupuesto en " + Math.abs(totalValue) + " pesos.");
        } else if (totalValue == 0) {
            System.out.println("Se alcanzó exactamente 1,000,000 pesos en productos.");
        }
    }


    // Transferir artículos de la lista a las colas
    private void transferItemsToQueues() {
        for (Item item : itemList) {
            switch (item.getName()) {
                case "Shampoo":
                    queue1.add(item);
                    break;
                case "Moisturizing Cream":
                    queue2.add(item);
                    break;
                case "Styling Wax":
                    queue3.add(item);
                    break;
                case "Hair Dryer":
                    queue4.add(item);
                    break;
                case "Hair Straightener":
                    queue5.add(item);
                    break;
            }
        }
        itemList.clear(); // Vaciar la lista después de transferir
    }

    public Queue<Item>[] getQueues() {
        return new Queue[]{queue1, queue2, queue3, queue4, queue5};
    }


    public void printQueuesAndItemList() {
        System.out.println("Contenido de las colas y la lista de inventario:");

        System.out.println("Queue 1 (Shampoo):");
        for (Item item : queue1) {
            System.out.println(item);
        }

        System.out.println("Queue 2 (Moisturizing Cream):");
        for (Item item : queue2) {
            System.out.println(item);
        }

        System.out.println("Queue 3 (Styling Wax):");
        for (Item item : queue3) {
            System.out.println(item);
        }

        System.out.println("Queue 4 (Hair Dryer):");
        for (Item item : queue4) {
            System.out.println(item);
        }

        System.out.println("Queue 5 (Hair Straightener):");
        for (Item item : queue5) {
            System.out.println(item);
        }

        System.out.println("Item List (debería estar vacía después de transferir a las colas):");
        for (Item item : itemList) {
            System.out.println(item);
        }
    }


}
