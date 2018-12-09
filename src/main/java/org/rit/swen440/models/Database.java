package org.rit.swen440.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:sqlite:products.db"; // SQLite connection string
            Connection conn = null;

            try {
                conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            connection = conn;
        }

        return connection;
    }

    public static void closeConnection() {
        try {

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void selectTest(java.sql.Connection conn){
        String sql = "SELECT * FROM category";

        try (Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)
        ){
            // loop through the result set
            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + "\t" +
                                rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
