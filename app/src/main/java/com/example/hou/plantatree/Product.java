package com.example.hou.plantatree;

public class Product {
    public String qty;
    public String name;
    public String price;
    public String age;
    public String height;


    public void setAttribute(String qty, String name, String price, String height, String age){
        this.qty=qty;
        this.price=price;
        this.name=name;
        this.height=height;
        this.age=age;
    }
}
