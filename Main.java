package cce;
import java.util.ArrayDeque;
import java.util.Scanner;

class WarehouseContainer {
    private String id;
    private String description;
    private String Quantity;

    public WarehouseContainer(String id, String description, String Quantity) {
        this.id = id;
        this.description = description;
        this.Quantity = Quantity;
    }

    @Override
    public String toString() {
        return id + " | " + description + " | " + Quantity;
    }
}

class truck {
    private String driver;
    private String plate;

    public truck(String driver, String plate) {
        this.driver = driver;
        this.plate = plate;
    }

    @Override
    public String toString() {
        return driver + " | PLATE " + plate;
    }
}

public class Main {   
    private static ArrayDeque<WarehouseContainer> warehouseStack = new ArrayDeque<>();
    private static ArrayDeque<truck> truckQueue = new ArrayDeque<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n=== Central WAREHOUSE SYSTEM ===");
            System.out.println("[1] Store Item ");
            System.out.println("[2] View WareHouse");
            System.out.println("[3] Register new Stock ");
            System.out.println("[4] View Queue trucks");
            System.out.println("[5] Load next Stock ");
            System.out.println("[0] Exit");
            System.out.print("Enter choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> storeItem();
                case 2 -> viewWarehouse();
                case 3 -> registertruck();
                case 4 -> viewWaitingtruck();
                case 5 -> loadNextShip();
                case 0 -> System.out.println("Exiting program CUTIE...");
                default -> System.out.println("Invalid choice! HAHAHA");
            }
        } while (choice != 0);
    }

    private static void storeItem() {
        System.out.print("Enter Item ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Description: ");
        String desc = sc.nextLine();
        System.out.print("Enter Quantity: ");
        String quantity = sc.nextLine();

        WarehouseContainer c = new WarehouseContainer(id, desc, quantity);
        warehouseStack.push(c);
        System.out.println("Stored: " + c);
    }

    private static void viewWarehouse() {
        if (warehouseStack.isEmpty()) {
            System.out.println("No Items in warehouse.");
            return;
        }
        System.out.println("\nTOP >");
        for (WarehouseContainer c : warehouseStack) {
            System.out.println(c);
        }
        System.out.println("< BOTTOM");
    }

    private static void registertruck() {
        System.out.print("Enter PlateNo: ");
        String plate = sc.nextLine();
        System.out.print("Enter Driver name: ");
        String driver = sc.nextLine();

        truck s = new truck(plate, driver);
        truckQueue.add(s);
        System.out.println("Registered: " + s);
    }

    private static void viewWaitingtruck() {
        if (truckQueue.isEmpty()) {
            System.out.println("No Trucks Waiting.");
            return;
        }
        System.out.println("\nFRONT >");
        for (truck s : truckQueue) {
            System.out.println(s);
        }
        System.out.println("< REAR");
    }

    private static void loadNextShip() {
        if (warehouseStack.isEmpty() || truckQueue.isEmpty()) {
            System.out.println("Cannot load. Either no containers or no ships waiting.");
            return;
        }
        WarehouseContainer c = warehouseStack.pop();
        truck s = truckQueue.poll();

        System.out.println("Loaded: " + c + " → " + s);
        System.out.println("Remaining containers: " + warehouseStack.size());
        System.out.println("Remaining ships waiting: " + truckQueue.size());
    }
}