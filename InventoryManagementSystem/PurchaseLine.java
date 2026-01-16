package InventoryManagementSystem;

import java.io.Serializable;

public class PurchaseLine implements Serializable {
    private int itemId;
    private int quantity;
    private double unitCost;

    public PurchaseLine(int itemId,int quantity,double unitCost){
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public int getItemId(){
        return itemId;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getUnitCost(){
        return unitCost;
    }

    public double lineTotal(){
        return unitCost * quantity;
    }

    @Override
    public String toString(){
        return String.format("Item %d | Qty: %d | Cost: %.2f | Total: %.2f",
                itemId,quantity,unitCost,lineTotal());
    }
}
