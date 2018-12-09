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
}
