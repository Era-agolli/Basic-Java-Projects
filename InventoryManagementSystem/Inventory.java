package InventoryManagementSystem;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Inventory implements Serializable {

    private Map<Integer, Item> items = new HashMap<>();
    private Map<Integer, Supplier> suppliers = new HashMap<>();
    private Map<Integer, PurchaseOrder> purchaseOrders = new HashMap<>();

    public Map<Integer, Item> getItems() {
        return items;
    }

    public Map<Integer, Supplier> getSuppliers() {
        return suppliers;
    }

    public Map<Integer, PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public boolean addItem(Item item) {
        if (items.containsKey(item.getId()))
            return false;
        items.put(item.getId(), item);
        return true;
    }

    public boolean addSupplier(Supplier supplier) {
        if (suppliers.containsKey(supplier.getId()))
            return false;
        suppliers.put(supplier.getId(), supplier);
        return true;
    }

    public boolean linkItemToSupplier(int itemId, int supplierId) {
        Item item = items.get(itemId);
        Supplier supplier = suppliers.get(supplierId);
        if (item == null || supplier == null)
            return false;
        item.setSupplierId(supplierId);
        return true;
    }

    public List<Item> searchItems(String keyword) {
        String k = keyword.toLowerCase();
        return items.values().stream()
                .filter(i -> i.getName().toLowerCase().contains(k) ||
                        i.getCategory().toLowerCase().contains(k))
                .sorted(Comparator.comparing(Item::getId))
                .collect(Collectors.toList());
    }

    public List<Item> lowStockItems(int threshold) {
        return items.values().stream()
                .filter(i -> i.getQuantity() <= threshold)
                .sorted(Comparator.comparing(Item::getQuantity))
                .collect(Collectors.toList());
    }

    public double totalInventoryValue() {
        return items.values().stream().mapToDouble(Item::inventoryValue).sum();
    }

    public boolean createPurchaseOrder(PurchaseOrder po) {
        if (purchaseOrders.containsKey(po.getPurchaseOrderId()))
            return false;
        if (!suppliers.containsKey(po.getSupplierId()))
            return false;
    purchaseOrders.put(po.getPurchaseOrderId(), po);
    return true;
}

    public boolean receivePurchaseOrder(int poId) {
        PurchaseOrder po = purchaseOrders.get(poId);
        if (po == null || po.isReceived())
            return false;
        for (PurchaseLine line : po.getLines()) {
            Item item = items.get(line.getItemId());
            if (item == null)
                return false; // invalid item in PO
        }
        for (PurchaseLine line : po.getLines()) {
            Item item = items.get(line.getItemId());
            item.increaseStock(line.getQuantity());
            item.setUnitPrice(line.getUnitCost()); // optional: update cost
        }
        po.markReceived();
        return true;
    }
}



