package org.rit.swen440.presentation;

import org.rit.swen440.control.Controller;
import org.rit.swen440.models.Product;
import org.rit.swen440.models.Transaction;
import org.rit.swen440.models.User;

import java.util.ArrayList;
import java.util.List;

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
            case 5:
                DateLevel();
                break;
            case 6:
                MonthLevel();
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

        user = controller.login(email, password);
        if (user == null) {
            System.out.println("Email or password is incorrect.");
        } else if (user.getAccountType().equals("admin")) {
            System.out.println("Login Successful.");
            System.out.println();
            currentLevel = 0;
        } else {
            System.out.println("Only admins may audit the system.");
        }
    }

    private void AuditLevel() {
        Menu m = new Menu();

        List<String> auditOptions = new ArrayList<>();
        auditOptions.add("View Orders Between Dates");
        auditOptions.add("Overview for Month");

        m.loadMenu(auditOptions);

        m.addMenuItem("'q' to Quit");
        System.out.println("The following audit options are available");
        m.printMenu();
        String result;
        try {
            result = m.getSelection();
        } catch (Exception e) {
            result = "q";
        }

        switch (result) {
            case "q":
                currentLevel--;
                break;
            case "0":
                currentLevel = 5;
                break;
            case "1":
                currentLevel = 6;
                break;
        }
    }

    private void DateLevel() {
        System.out.print("Start Date (YYYY-MM-DD): ");
        Menu m = new Menu();
        String start = m.getSelection();

        System.out.print("End Date (YYYY-MM-DD): ");
        m = new Menu();
        String end = m.getSelection();

        List<Transaction> transactions = controller.getTransactionsBetweenDates(start, end);
        if (transactions.size() > 0) {
            System.out.println(transactions);
        } else {
            System.out.println("No transactions found between specified dates.");
            System.out.println();
        }

        currentLevel = 0;
    }

    private void MonthLevel() {
        System.out.print("Month (YYYY-MM): ");
        Menu m = new Menu();
        String month = m.getSelection();

        List<Product> products = controller.getTopAndBottomSoldProducts(month);

        if (products.size() < 2) {
            System.out.println("No transactions found between specified dates");
            System.out.println("");
            currentLevel = 0;
            return;
        }

        int curr = products.get(0).getCount();
        System.out.println("Top sellers of the month:");
        for (Product product: products) {
            if (product.getCount() < curr)
                System.out.println("Bottom sellers of the month:");

            System.out.println("    " + product.toString() + ": " + product.getCount());
        }

        System.out.println("");

        currentLevel = 0;
    }
}
