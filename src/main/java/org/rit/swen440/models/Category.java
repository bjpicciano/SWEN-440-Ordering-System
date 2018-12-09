package org.rit.swen440.models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private String description;

    static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        return categories;
    }
}
