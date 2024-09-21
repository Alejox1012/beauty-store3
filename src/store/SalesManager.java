package store;

import item.Item;

import java.util.*;

public class SalesManager {
    private int totalItemsSold = 0;
    private double totalValueSold = 0;


    public void sellItem(Queue<Item> queue, Scanner scanner) {
        if (queue.isEmpty()) {
            System.out.println("No hay stock disponible para este tipo de artículo.");
            return;
        }

        // Agrupar los artículos por marca y calcular las cantidades totales por marca
        Map<String, Integer> brandQuantities = new HashMap<>();
        for (Item item : queue) {
            brandQuantities.put(item.getBrand(), brandQuantities.getOrDefault(item.getBrand(), 0) + item.getQuantity());
        }

        // Mostrar marcas disponibles junto con las unidades disponibles
        List<String> brands = new ArrayList<>(brandQuantities.keySet());
        System.out.println("Marcas disponibles:");
        for (int i = 0; i < brands.size(); i++) {
            String brand = brands.get(i);
            System.out.println((i + 1) + ". " + brand + " - Unidades disponibles: " + brandQuantities.get(brand));
        }

        // Solicitar selección de marca
        System.out.print("Seleccione el número de la marca que desea comprar: ");
        int brandChoice = scanner.nextInt() - 1;

        if (brandChoice < 0 || brandChoice >= brands.size()) {
            System.out.println("Selección de marca inválida.");
            return;
        }

        String selectedBrand = brands.get(brandChoice);
        int availableQuantity = brandQuantities.get(selectedBrand);

        // Mostrar unidades disponibles
        System.out.println("Unidades disponibles de " + queue.peek().getName() + " - Marca " + selectedBrand + ": " + availableQuantity);

        // Solicitar cantidad que se desea comprar
        System.out.print("Ingrese la cantidad que desea comprar: ");
        int quantity = scanner.nextInt();

        // Verificar si hay suficiente cantidad
        if (availableQuantity >= quantity) {
            // Actualizar el total vendido
            totalItemsSold += quantity;
            double totalPrice = 0;

            // Actualizar la cantidad en la cola original
            Iterator<Item> iterator = queue.iterator();
            while (iterator.hasNext() && quantity > 0) {
                Item item = iterator.next();
                if (item.getBrand().equals(selectedBrand)) {
                    if (item.getQuantity() > quantity) {
                        totalPrice += item.getPrice() * quantity;
                        item.reduceQuantity(quantity);
                        quantity = 0;
                    } else {
                        totalPrice += item.getPrice() * item.getQuantity();
                        quantity -= item.getQuantity();
                        iterator.remove(); // Eliminar artículo de la cola
                    }
                }
            }

            totalValueSold += totalPrice;
            System.out.println("Compra realizada con éxito.");
            System.out.println("Venta del artículo con marca: " + selectedBrand);
        } else {
            System.out.println("Stock insuficiente. Disponible: " + availableQuantity);
        }
    }

    public int getTotalItemsSold() {
        return totalItemsSold;
    }

    public double getTotalValueSold() {
        return totalValueSold;
    }

    // Calcular el valor total de artículos pendientes en todas las colas
    public double calculatePendingTotals(Queue<Item>... queues) {
        double total = 0;
        for (Queue<Item> queue : queues) {
            for (Item item : queue) {
                total += item.getPrice() * item.getQuantity();
            }
        }
        return total;
    }

    // Calcular la cantidad total de artículos pendientes en todas las colas
    public int calculatePendingItems(Queue<Item>... queues) {
        int total = 0;
        for (Queue<Item> queue : queues) {
            for (Item item : queue) {
                total += item.getQuantity();
            }
        }
        return total;
    }
}
