package com.example.hou.plantatree;

public class Product {
    public String qty;
    public String name;
    public String price;


    public void setAttribute(String qty, String name, String price){
        this.qty=qty;
        this.price=price;
        this.name=name;
    }
}
