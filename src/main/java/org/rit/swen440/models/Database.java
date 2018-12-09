package org.rit.swen440.models;

import java.sql.*;

public class Database {
    Connection connection;

    public Database() {
        this.connection = getConnection();
    }

    private Connection getConnection() {
        String url = "jdbc:sqlite:products.db"; // SQLite connection string
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public ResultSet query(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }

    public void insertTransaction(String date_ordered, String date_shipped, String date_received, int user_id) throws SQLException {
        String sql = "INSERT INTO transactions (date_ordered, date_shipped, date_received, user_id) VALUES(?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, date_ordered);
        pstmt.setString(2, date_shipped);
        pstmt.setString(3, date_received);
        pstmt.setInt(4, user_id);
        pstmt.executeUpdate();
    }

    public void insertTransactionProduct(int transaction_id, int product_sku, float purchase_price, int quantity) throws SQLException {
        String sql = "INSERT INTO transaction_product (transaction_id, product_sku, purchase_price, quantity) VALUES(?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, transaction_id);
        pstmt.setInt(2, product_sku);
        pstmt.setFloat(3, purchase_price);
        pstmt.setInt(4, quantity);
        pstmt.executeUpdate();
    }
}
