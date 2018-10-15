package com.example.arshu.assignment2;

/**
 * Created by Arshu on 2017-11-15.
 */

public class Product {
    private int productID;
    private String name;
    private String description;
    private float price;

    public Product(String name, String description, float price){
        this.name=name;
        this.description =description;
        this.price=price;
    }

    public float getPrice() {return price;}

    public void setPrice(float price){this.price = price;}

    public int getProductID() {return productID;}

    public void setProductID(int productID) {this.productID = productID;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
