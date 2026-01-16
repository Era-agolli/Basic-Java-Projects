package InventoryManagementSystem;

import java.io.Serializable;

public class Supplier implements Serializable {

    private int id;
    private String name;
    private String contact;

    public Supplier(int id,String name,String contact){
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getContact(){
        return contact;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    @Override
    public String toString(){
        return String.format("%d | %s | %s",id,name,contact);
    }
}
