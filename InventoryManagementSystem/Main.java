package InventoryManagementSystem;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static InventoryManagementSystem.InputHelpers.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryStore store = new InventoryStore();
    private static Inventory inv = store.load();
    public static void main(String[] args) {

        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> addItem();
                case 2 -> listItems();
                case 3 -> adjustStock();
                case 4 -> searchItems();
                case 5 -> addSupplier();
                case 6 -> listSuppliers();
                case 7 -> linkItemSupplier();
                case 8 -> createPurchaseOrder();
                case 9 -> listPurchaseOrders();
                case 10 -> receivePurchaseOrder();
                case 11 -> reports();
                case 12 -> exit();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Inventory Management System ===");
        System.out.println("1. Add New Item");
        System.out.println("2. Show All Items");
        System.out.println("3. Adjust Stock (Add/Remove)");
        System.out.println("4. Search Items");
        System.out.println("5. Register Supplier");
        System.out.println("6. Show All Suppliers");
        System.out.println("7. Link Item to Supplier");
        System.out.println("8. Create Purchase Order");
        System.out.println("9. Show Purchase Orders");
        System.out.println("10. Receive Purchase Order");
        System.out.println("11. Reports (Low Stock / Inventory Value)");
        System.out.println("12. Exit Program");
    }

    private static void addItem() {
        int id = readInt("Item ID: ");
        String name = readLine("Name: ");
        String category = readLine("Category: ");
        int qty = readInt("Initial Quantity: ");
        double price = readDouble("Unit Price: ");
        Integer supplierId = null;
        String link = readLine("Link to supplier now? (y/n): ");
        if (link.equalsIgnoreCase("y")) {
            supplierId = readInt("Supplier ID: ");
            if (!inv.getSuppliers().containsKey(supplierId)) {
                System.out.println("Supplier not found. Skipping link.");
                supplierId = null;
            }
        }
        boolean ok = inv.addItem(new Item(id, name, category, qty, price, supplierId));
        if (ok) {
            store.save(inv);
            System.out.println("Item added.");
        } else {
            System.out.println("Item ID already exists.");
        }
    }

    private static void listItems() {
        if (inv.getItems().isEmpty()) {
            System.out.println("No items found.");
            return;
        }
        inv.getItems().values().stream()
                .sorted(Comparator.comparing(Item::getId))
                .forEach(System.out::println);
    }
    private static void adjustStock() {
        int id = readInt("Item ID: ");
        Item item = inv.getItems().get(id);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        System.out.println("1) Increase stock 2) Decrease stock");
        int opt = readInt("Choose: ");
        int amount = readInt("Amount: ");
        if (opt == 1) {
            item.increaseStock(amount);
            store.save(inv);
            System.out.println("Stock increased.");
        } else if (opt == 2) {
            if (item.decreaseStock(amount)) {
                store.save(inv);
                System.out.println("Stock decreased.");
            } else {
                System.out.println("Insufficient stock.");
            }
        } else {
            System.out.println("Invalid option.");
        }
    }

    private static void searchItems() {
        String k = readLine("Keyword (name/category): ");
        List<Item> results = inv.searchItems(k);
        if (results.isEmpty())
            System.out.println("No matches.");
        else
            results.forEach(System.out::println);
    }

    private static void addSupplier() {
        int id = readInt("Supplier ID: ");
        String name = readLine("Name: ");
        String contact = readLine("Contact: ");
        boolean ok = inv.addSupplier(new Supplier(id, name, contact));
        if (ok) {
            store.save(inv);
            System.out.println("Supplier added.");
        } else {
            System.out.println("Supplier ID already exists.");
        }
    }

    private static void listSuppliers() {
        if (inv.getSuppliers().isEmpty()) {
            System.out.println("No suppliers found.");
            return;
        }
        inv.getSuppliers().values().stream()
                .sorted(Comparator.comparing(Supplier::getId))
                .forEach(System.out::println);
    }

    private static void linkItemSupplier() {
        int itemId = readInt("Item ID: ");
        int supplierId = readInt("Supplier ID: ");
        boolean ok = inv.linkItemToSupplier(itemId, supplierId);
        if (ok) {
            store.save(inv);
            System.out.println("Linked item to supplier.");
        } else {
            System.out.println("Invalid item or supplier.");
        }
    }

    private static void createPurchaseOrder() {
        int poId = readInt("PO ID: ");
        int supplierId = readInt("Supplier ID: ");
        if (!inv.getSuppliers().containsKey(supplierId)) {
            System.out.println("Supplier not found.");
            return;
        }
        PurchaseOrder po = new PurchaseOrder(poId, supplierId);
        while (true) {
            String add = readLine("Add line? (y/n): ");
            if (!add.equalsIgnoreCase("y"))
                break;
            int itemId = readInt("Item ID: ");
            if (!inv.getItems().containsKey(itemId)) {
                System.out.println("Item not found.");
                continue;
            }
            int qty = readInt("Quantity: ");
            double cost = readDouble("Unit Cost: ");
            po.addLine(new PurchaseLine(itemId, qty, cost));
        }
        boolean ok = inv.createPurchaseOrder(po);
        if (ok) {
            store.save(inv);
            System.out.println("PO created: " + po);
        } else {
            System.out.println("PO ID exists or supplier invalid.");
        }
    }

    private static void listPurchaseOrders() {
        if (inv.getPurchaseOrders().isEmpty()) {
            System.out.println("No purchase orders.");
            return;
        }
        inv.getPurchaseOrders().values().stream()
                .sorted(Comparator.comparing(PurchaseOrder::getPurchaseOrderId))
                .forEach(po -> {
                    System.out.println(po);
                    po.getLines().forEach(line -> System.out.println(" - " + line));
                });
    }

    private static void receivePurchaseOrder() {
        int poId = readInt("PO ID to receive: ");
        boolean ok = inv.receivePurchaseOrder(poId);
        if (ok) {
            store.save(inv);
            System.out.println("PO received and stock updated.");
        } else {
            System.out.println("Invalid PO or already received, or item missing.");
        }
    }

    private static void reports() {
        System.out.println("\n--- Reports ---");
        System.out.println("1) Low Stock Items");
        System.out.println("2) Total Inventory Value");
        int opt = readInt("Choose: ");
        switch (opt) {
            case 1 -> {
                int threshold = readInt("Threshold (qty <=): ");
                List<Item> low = inv.lowStockItems(threshold);
                if (low.isEmpty())
                    System.out.println("No low stock items.");
                else low.forEach(System.out::println);
            }
            case 2 -> {
                double total = inv.totalInventoryValue();
                System.out.printf("Total Inventory Value: %.2f%n", total);
            }
            default -> System.out.println("Invalid option.");
        }
    }

    private static void exit() {
        store.save(inv);
        System.out.println("Exiting... Goodbye!");
        System.exit(0);
    }
}
