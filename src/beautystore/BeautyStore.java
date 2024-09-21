package beautystore;

import item.Item;
import store.InventoryManager;
import store.SalesManager;

import java.util.Queue;
import java.util.Scanner;

public class BeautyStore {
    public static void main(String[] args) {

        InventoryManager inventoryManager = new InventoryManager();
        inventoryManager.printQueuesAndItemList(); // Mostrar inventario

        SalesManager salesManager = new SalesManager();

        Queue<Item>[] queues = inventoryManager.getQueues();

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.println("=== Menú ===");
            System.out.println("1. Comprar artículos");
            System.out.println("2. Finalizar programa");
            System.out.print("Seleccione una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Opción para comprar artículos
                    System.out.println("Seleccione el tipo de artículo:");
                    System.out.println("1. Shampoo");
                    System.out.println("2. Moisturizing Cream");
                    System.out.println("3. Styling Wax");
                    System.out.println("4. Hair Dryer");
                    System.out.println("5. Hair Straightener");
                    System.out.print("Seleccione el número del artículo: ");
                    int articleType = scanner.nextInt();

                    Queue<Item> selectedQueue = null;
                    if (articleType >= 1 && articleType <= 5) {
                        selectedQueue = queues[articleType - 1];
                    } else {
                        System.out.println("Opción inválida.");
                        continue;
                    }


                    salesManager.sellItem(selectedQueue, scanner);
                    break;

                case 2:
                    // Opción para finalizar el programa
                    running = false;

                    double totalValuePending = salesManager.calculatePendingTotals(queues);
                    int totalItemsPending = salesManager.calculatePendingItems(queues);

                    // Mostrar el resumen final
                    System.out.println("=== Resumen de Ventas ===");
                    System.out.println("Total de artículos vendidos: " + salesManager.getTotalItemsSold());
                    System.out.println("Valor total de artículos vendidos: $" + salesManager.getTotalValueSold());
                    System.out.println("Total de artículos pendientes por vender: " + totalItemsPending);
                    System.out.println("Valor total de artículos pendientes: $" + totalValuePending);
                    System.out.println("Diferencia entre artículos vendidos y pendientes: $" +
                            (totalValuePending - salesManager.getTotalValueSold()));
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
