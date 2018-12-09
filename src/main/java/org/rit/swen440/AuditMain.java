package org.rit.swen440;

import org.rit.swen440.presentation.AuditManager;

public class AuditMain
{
    public static void main(String[] args)
    {
        System.setProperty("fileSystemRoot", "./orderSys");

        AuditManager mgr = new AuditManager();
        boolean done = false;
        do {
            done = mgr.loadLevel();
        } while (!done);

        System.out.println("Thank you for using Hippolyta.com!");
    }
}