package org.rit.swen440.presentation;

import org.rit.swen440.control.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class menumgr {

    private int currentLevel = 0;
    private String currentCategoryName;
    private String currentItemName;
    private Controller controller;

    public menumgr() {
        //TODO Database: Use New Controller Class
        controller = new Controller(System.getProperty("fileSystemRoot"));

    }

    public boolean loadLevel() {
        switch (currentLevel) {
            case -1:
                return true;
            case 0:
                CategoryLevel();
                break;
            case 1:
                ItemLevel();
                break;
            default:
                System.out.println("Returning to main org.rit.swen440.presentation.menu");
                currentLevel = 0;
                CategoryLevel();
                break;
        }

        return false;
    }

    private void CategoryLevel() {
        menu m = new menu();
        //TODO Database: "SELECT * FROM category"
        List<String> categories = controller.getCategories();
        m.loadMenu(categories);

        m.addMenuItem("'q' to Quit"); 
        System.out.println("The following org.rit.swen440.presentation.categories are available");
        m.printMenu();
        String result = "0";
        try {
            result = m.getSelection();
        }
        catch (Exception e) {
            result = "q";
        }
        if (Objects.equals(result,"q")) {
            currentLevel--;
        }
        else {
            currentLevel++;
            int iSel = Integer.parseInt(result);

            currentCategoryName = categories.get(iSel);
            System.out.println("\nYour Selection was:" + currentCategoryName);
        }
    }

    private void ItemLevel() {
        menu m = new menu();

        //TODO Database: "SELECT * FROM product WHERE category_name = currentCategoryName"
        List<String> itemList = controller.getProducts(currentCategoryName);
        List<String> l = new ArrayList<>();

        System.out.println("");
        for (String itm: itemList)
            l.add(controller.getProductInformation(currentCategoryName, itm, Controller.PRODUCT_FIELD.NAME)
             + "($" + controller.getProductInformation(currentCategoryName, itm, Controller.PRODUCT_FIELD.COST) + ")");
        
        m.loadMenu(l);
        m.addMenuItem("'q' to Quit");
        System.out.println("The following items are available");
        m.printMenu();
        String result = m.getSelection();
        try {
            int iSel = Integer.parseInt(result); //Item selected
            currentItemName = itemList.get(iSel);

            //Now read the file and print the org.rit.swen440.presentation.items in the catalog
            System.out.println("You want item from the catalog: " + currentItemName);
        }
        catch (Exception e) {
            result = "q";
        }
        if (result.equalsIgnoreCase("q"))
            currentLevel--;
        else {
            OrderQty(currentCategoryName, currentItemName);
        }
    }

    //Order Level
    private void OrderQty(String category, String item) {
        System.out.println("Please select a quantity");

        //TODO Database: "SELECT * FROM product WHERE name = currentItemName"
        System.out.println(controller.getProductInformation(category, item, Controller.PRODUCT_FIELD.NAME) +
                " availability:" + controller.getProductInformation(category, item, Controller.PRODUCT_FIELD.INVENTORY));
        System.out.print(":");
        menu m = new menu();
        String result = m.getSelection();

        //TODO Check for Availability of Quantity

        //TODO Database: "INSERT INTO transaction (uniqueId, currentDate, null, null, clientId)"
        //TODO Database: "INSERT INTO transaction_product (uniqueId, product.sku, product.price, result"

        System.out.println("You ordered:" + result);
    }
}