package org.rit.swen440.control;


import org.rit.swen440.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controls access to data, on start-up scans directories and builds internal
 * representation of categories and items within each category.  Isolates the
 * categories and products from information on the underlying file system.
 */
public class Controller {
    private Database database;

    public Controller() {
        database = new Database();
    }

    public List<Transaction> queryAuditLog(String predicate) {
        List<Transaction> transactions = new ArrayList<>();

        try {
            ResultSet rs = database.query(
            "SELECT\n" +
                "       id,\n" +
                "       date_ordered,\n" +
                "       date_shipped,\n" +
                "       date_received,\n" +
                "       user_id\n" +
                "FROM transactions\n" +
                predicate
            );

            while (rs.next()) {
                int id = rs.getInt("id");
                String dateOrdered = rs.getString("date_ordered");
                String dateShipped = rs.getString("date_shipped");
                String dateReceived = rs.getString("date_received");
                int userId = rs.getInt("user_id");

                User user = getUserById(userId);
                List<TransactionProduct> transactionProducts = getTransactionProductsByTransactionId(id);

                transactions.add(new Transaction(id, user, transactionProducts, dateOrdered, dateShipped, dateReceived));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactions;
    }

    public List<Transaction> getTransactionsBetweenDates(String startDate, String endDate) {
        List<Transaction> transactions = new ArrayList<>();

        String predicate = "WHERE DATE(date_shipped) BETWEEN DATE('" + startDate + "') AND DATE('" + endDate + "')";
        transactions = queryAuditLog(predicate);

        return transactions;
    }

    public User login(String email, String password) {
        try {
            ResultSet rs = database.query(
            "SELECT\n" +
                "       id,\n" +
                "       email\n" +
                "FROM user\n" +
                "WHERE\n" +
                "    email = '" + email + "'\n" +
                "AND\n" +
                "    password = " + password
            );

            int id = rs.getInt("id");

            String userType = getUserTypeById(id);

            return new User(id, email, userType);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public User getUserById(int id) {
        try {
            ResultSet rs = database.query(
            "SELECT\n" +
                "       id,\n" +
                "       email\n" +
                "FROM user\n" +
                "WHERE id = " + id
            );

            String email = rs.getString("email");

            String userType = getUserTypeById(id);

            return new User(id, email, userType);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean isUserInUserType(int id, String userType) {
        try {
            ResultSet rs = database.query(
            "SELECT user_id FROM " + userType + "\n" +
                "WHERE user_id = " + id
            );

            int userId = rs.getInt("user_id");

            return id == userId;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    private String getUserTypeById(int id) {
        String userType = null;
        boolean isClient = isUserInUserType(id, "client");
        if (isClient) userType = "client";
        else {
            boolean isAdmin = isUserInUserType(id, "admin");
            if (isAdmin) userType = "admin";
        }
        if (userType == null) throw new IllegalArgumentException("user is neither a client or admin");

        return userType;
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try {
            ResultSet rs = database.query("SELECT name, description FROM category");

            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");

                categories.add(new Category(name, description));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categories;
    }

    public Category getCategoryByName(String name) {
        try {
            ResultSet rs = database.query("SELECT description FROM category WHERE name = '" + name + "'");

            String description = rs.getString("description");

            return new Category(name, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Product getProductBySKU(int sku) {
        try {
            ResultSet rs = database.query("SELECT\n" +
                "       sku,\n" +
                "       count,\n" +
                "       name,\n" +
                "       description,\n" +
                "       price,\n" +
                "       category_name\n" +
                "FROM product\n" +
                "WHERE sku = " + sku
            );

            int count = rs.getInt("count");
            String name = rs.getString("name");
            String description = rs.getString("description");
            float price = rs.getFloat("price");
            String categoryName = rs.getString("category_name");
            Category category = getCategoryByName(categoryName);

            return new Product(sku, count, name, description, price, category);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public Optional<Product> getAllProducts() {
        return null;
        //TODO
    }

    public List<Product> getProductsInCategory(String category_name) {
        List<Product> products = new ArrayList<>();

        try {
            ResultSet rs = database.query(
            "SELECT\n" +
                "       sku,\n" +
                "       count,\n" +
                "       name,\n" +
                "       description,\n" +
                "       price,\n" +
                "       category_name\n" +
                "FROM product\n" +
                "WHERE category_name = '" + category_name.toLowerCase() + "'"
            );

            while (rs.next()) {
                int sku = rs.getInt("sku");
                int count = rs.getInt("count");
                String name = rs.getString("name");
                String description = rs.getString("description");
                float price = rs.getFloat("price");
                Category category = getCategoryByName(category_name);

                products.add(new Product(sku, count, name, description, price, category));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return products;
    }

    public boolean createTransaction(User client, List<Product> products, int quantity) {
        String date_ordered = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        int client_id = client.getId();
        try {
            database.insertTransaction(date_ordered, null, null, client_id);
            ResultSet rs = database.query("SELECT seq FROM sqlite_sequence WHERE name = 'transactions'");
            int transaction_id = rs.getInt("seq");
            rs.close();
            for(Product product : products ) {
                boolean success = createTransactionProduct(transaction_id, product, quantity);
                if(!success) {
                    System.out.println("Error creating transaction product");
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Transaction getTransactionById(int id) {
        List<TransactionProduct> transactionProducts = getTransactionProductsByTransactionId(id);

        try {
            ResultSet rs = database.query(
            "SELECT\n" +
                "       id,\n" +
                "       date_ordered,\n" +
                "       date_shipped,\n" +
                "       date_received,\n" +
                "       user_id\n" +
                "FROM transactions\n" +
                "WHERE id = " + id
            );

            String dateOrdered = rs.getString("date_ordered");
            String dateShipped = rs.getString("date_shipped");
            String dateReceived = rs.getString("date_received");

            int userId = rs.getInt("user_id");
            User user = getUserById(userId);

            return new Transaction(id, user, transactionProducts, dateOrdered, dateShipped, dateReceived);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    private boolean createTransactionProduct(int transaction_id, Product product, int quantity) {
        try {
            database.insertTransactionProduct(transaction_id, product.getSku(), product.getPrice(), quantity);
            database.updateCount(product.getSku(), quantity);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<TransactionProduct> getTransactionProductsByTransactionId(int transactionId) {
        List<TransactionProduct> transactionProducts = new ArrayList<>();

        try {
            ResultSet rs = database.query(
            "SELECT\n" +
                "       transaction_id,\n" +
                "       product_sku,\n" +
                "       purchase_price,\n" +
                "       quantity\n" +
                "FROM transaction_product\n" +
                "WHERE transaction_id = " + transactionId
            );

            while (rs.next()) {
                int sku = rs.getInt("product_sku");
                Product product = getProductBySKU(sku);

                float purchasePrice = rs.getFloat("purchase_price");
                int quantity = rs.getInt("quantity");

                transactionProducts.add(new TransactionProduct(product, purchasePrice, quantity));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactionProducts;
    }
}
