package InventoryManagementSystem;

import java.io.Serial;
import java.io.Serializable;

public class Item implements Serializable {

    private int id;
    private String name;
    private String category;
    private int quantity;
    private double unitPrice;
    private Integer supplierId;

    public Item(int id,String name,String category,int quantity,double unitPrice,Integer supplierId){
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplierId = supplierId;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getUnitPrice(){
        return unitPrice;
    }

    public Integer getSupplierId(){
        return supplierId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setUnitPrice(double unitPrice){
        this.unitPrice = unitPrice;
    }

    public void setSupplierId(Integer supplierId){
        this.supplierId = supplierId;
    }

    public void increaseStock(int amount){
        if (amount < 0 )
            throw new IllegalArgumentException("Amount must be positive.");
        quantity += amount;
    }

    public boolean decreaseStock(int amount){
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be positive.");
        if (quantity >= amount){
            quantity -= amount;
            return true;
        }
        return false;
    }

    public double inventoryValue(){
        return unitPrice * quantity;
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %s | Qty: %d | Price: %.2f | Supplier: %s",
                id,name,category,quantity,unitPrice,
                supplierId == null ? "-" : supplierId.toString());
    }
}
