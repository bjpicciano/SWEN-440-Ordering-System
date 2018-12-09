package org.rit.swen440.models;

import java.text.NumberFormat;

public class Product {
    private int sku;
    private int count;
    private String name;
    private String description;
    private float price;
    private Category category;
    static NumberFormat formatter = NumberFormat.getCurrencyInstance();

    public Product(int sku, int count, String name, String description, float price, Category category) {
        this.sku = sku;
        this.count = count;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public int getCount() {
        return this.count;
    }

    public int getSku() {
        return this.sku;
    }

    public float getPrice() {
        return this.price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return name + " (" + formatter.format(price) + ")";
    }
}
