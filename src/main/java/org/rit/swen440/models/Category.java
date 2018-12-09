package org.rit.swen440.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try {
            ResultSet rs = Database.query("SELECT name, description FROM category");
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");

                categories.add(new Category(name, description));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categories;
    }

    public static Category getCategoryByName(String name) {
        try {
            ResultSet rs = Database.query("SELECT description FROM category WHERE name = '" + name + "'");

            String description = rs.getString("description");
            rs.close();

            return new Category(name, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
