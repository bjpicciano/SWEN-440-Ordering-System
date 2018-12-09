package org.rit.swen440;

import org.rit.swen440.models.Database;
import org.rit.swen440.presentation.menumgr;

import java.sql.Connection;

public class menutest
{
    public static void main(String[] args)
    {
        Connection conn = Database.getConnection();
        Database.selectTest(conn);

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