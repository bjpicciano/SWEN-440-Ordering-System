package org.rit.swen440.presentation;

import org.rit.swen440.control.Controller;
import org.rit.swen440.models.User;

public class AuditManager {
    private int currentLevel = 2;
    private User user;
    private Controller controller;

    public AuditManager() {
        controller = new Controller();
    }

    public boolean loadLevel() {
        switch (currentLevel) {
            case -1:
                return true;
            case 0:
                AuditLevel();
                break;
            case 2:
                LoginLevel();
                break;
            default:
                System.out.println("Exiting...");
                currentLevel = -1;
                break;
        }

        return false;
    }

    private void LoginLevel() {
        System.out.print("Email: ");
        Menu m = new Menu();
        String email = m.getSelection();

        System.out.print("Password: ");
        m = new Menu();
        String password = m.getSelection();

        //TODO User Login
        System.out.println("Logging in as " + email + " with password " + password);
        //user = Controller.login(username, password);

        currentLevel = 0;
    }

    private void AuditLevel() {
        System.out.print("Email: ");
        Menu m = new Menu();
        String email = m.getSelection();

        System.out.print("Password: ");
        m = new Menu();
        String password = m.getSelection();

        //TODO User Login
        System.out.println("Logging in as " + email + " with password " + password);
        //user = Controller.login(username, password);

        currentLevel = 0;
    }
}
