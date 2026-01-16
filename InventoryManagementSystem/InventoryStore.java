package InventoryManagementSystem;

import java.io.*;

public class InventoryStore {

    private final String file = "inventory_store.dat";

    public void save(Inventory inv) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(inv);
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    public Inventory load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Inventory) ois.readObject();
        } catch (Exception e) {
            return new Inventory();
        }
    }
}
