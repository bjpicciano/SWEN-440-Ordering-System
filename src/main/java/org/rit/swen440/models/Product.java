package org.rit.swen440.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int sku;
    private int count;
    private String name;
    private String description;
    private float price;
    private Category category;

    private Product(int sku, int count, String name, String description, float price, Category category) {
        this.sku = sku;
        this.count = count;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    static Product getProductBySKU(int sku) {
        try {
            ResultSet rs = Database.query("SELECT sku, count, name, description, price, category_name FROM product WHERE sku = '" + sku + "'");

            int count = rs.getInt("count");
            String name = rs.getString("name");
            String description = rs.getString("description");
            float price = rs.getFloat("price");
            String category_name = rs.getString("category_name");

            rs.close();

            return new Product(sku, count, name, description, price, Category.getCategoryByName(category_name));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static List<Product> getProductsInCategory(String category_name) {
        List<Product> products = new ArrayList<>();
        try {
            ResultSet rs = Database.query("SELECT sku, count, name, description, price FROM product WHERE category_name = '" + category_name.toLowerCase() + "'");
            while (rs.next()) {
                int sku = rs.getInt("sku");
                int count = rs.getInt("count");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");

                products.add(new Product(sku, count, name, description, price, Category.getCategoryByName(category_name)));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return products;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}
