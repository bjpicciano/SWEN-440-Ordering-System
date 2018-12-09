package org.rit.swen440;

import org.rit.swen440.presentation.MenuManager;

public class MenuMain {
    public static void main(String[] args) {
        System.setProperty("fileSystemRoot", "./orderSys");

        MenuManager mgr = new MenuManager();
        boolean done = false;
        do {
            done = mgr.loadLevel();
        } while (!done);

        System.out.println("Thank you for shopping at Hippolyta.com!");
    }
}