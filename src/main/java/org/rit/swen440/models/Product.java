package org.rit.swen440.models;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int sku;
    private int count;
    private String name;
    private String description;
    private float price;
    private Category category;

    public Product() {

    }

    static Product getProductBySKU() {
        Product product = new Product();
        return product;
    }

    static List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        return products;
    }

    static List<Product> getProductsInCategory(String categoryName) {
        List<Product> products = new ArrayList<>();
        return products;
    }
}
