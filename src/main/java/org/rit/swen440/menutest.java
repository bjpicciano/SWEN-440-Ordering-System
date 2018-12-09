package org.rit.swen440;

import org.rit.swen440.presentation.menumgr;
import org.rit.swen440.models.*;

public class menutest
{
    public static void main(String[] args)
    {
//        System.out.println(Transaction.getTransactionById(1));
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