package org.rit.swen440;

import org.rit.swen440.models.Database;
import org.rit.swen440.presentation.menumgr;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class menutest
{
    public static void main(String[] args)
    {
        try {
            ResultSet rs = Database.query("SELECT * FROM category");
            while (rs.next()) {
                System.out.println(
                    rs.getString("name") + "\t" +
                    rs.getString("description")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.setProperty("fileSystemRoot", "./orderSys");
        System.out.println("Hello");
        menumgr mgr = new menumgr();
        boolean done = false;
        do {
            done = mgr.loadLevel();
        } while (!done);

        System.out.println("Thank you for shopping at Hippolyta.com!");
    }
}