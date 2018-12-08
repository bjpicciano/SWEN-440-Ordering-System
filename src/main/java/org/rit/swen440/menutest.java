package org.rit.swen440;

import org.rit.swen440.presentation.menumgr;

import java.io.FileInputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class menutest
{
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:products.db";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    private static void selectTest(Connection conn){
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

    public static void main(String[] args)
    {
        Connection conn = connect();
        selectTest(conn);

        try {
            System.getProperties().load(new FileInputStream("orderSys.properties"));
            System.out.println("Hello");
            menumgr mgr = new menumgr();
            int currentLevel = 0;
            boolean done = false;
            do {
                done = mgr.loadLevel(currentLevel);
            } while (!done);

            System.out.println("Thank you for shopping at Hippolyta.com!");

        } catch(IOException e) {
            System.err.println("orderSys.properties not found.");
        }
    }
}