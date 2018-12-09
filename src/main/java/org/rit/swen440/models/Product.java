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
        Product product = null;

        try {
            ResultSet rs = Database.query("SELECT\n" +
                    "       sku,\n" +
                    "       count,\n" +
                    "       name,\n" +
                    "       description,\n" +
                    "       price,\n" +
                    "       category_name\n" +
                    "FROM product\n" +
                    "WHERE sku = " + sku);

            int count = rs.getInt("count");
            String name = rs.getString("name");
            String description = rs.getString("description");
            float price = rs.getFloat("price");
            String categoryName = rs.getString("category_name");
            Category category = Category.getCategoryByName(categoryName);

            product = new Product(sku, count, name, description, price, category);

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

    public static List<Product> getProductsInCategory(String category_name) {
        List<Product> products = new ArrayList<>();

        try {
            ResultSet rs = Database.query(
                    "SELECT\n" +
                    "       sku,\n" +
                    "       count,\n" +
                    "       name,\n" +
                    "       description,\n" +
                    "       price,\n" +
                    "       category_name\n" +
                    "FROM product\n" +
                    "WHERE category_name = '" + category_name.toLowerCase() + "'");

            while (rs.next()) {
                int sku = rs.getInt("sku");
                int count = rs.getInt("count");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Category category = Category.getCategoryByName(category_name);

                products.add(new Product(sku, count, name, description, price, category));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    @Override
    public String toString() {
        return "Product{" +
                "sku=" + sku +
                ", name='" + name + '\'' +
                '}';
    }
}
