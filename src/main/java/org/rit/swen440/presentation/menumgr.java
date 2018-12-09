package org.rit.swen440.presentation;

import org.rit.swen440.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class menumgr {

    private int currentLevel = 2;
    private String currentCategoryName;
    private User user;

    public menumgr() {
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
            case 2:
                LoginLevel();
                break;
            default:
                System.out.println("Returning to main org.rit.swen440.presentation.menu");
                currentLevel = 0;
                CategoryLevel();
                break;
        }

        return false;
    }

    private void LoginLevel() {
        //TODO User Login
        user = new User();

        currentLevel = 0;
    }

    private void CategoryLevel() {
        menu m = new menu();

        List<Category> categoriesList = Category.getAllCategories();

        List<String> categories = new ArrayList<>();
        for(Category category: categoriesList){
            categories.add(category.getName());
        }

        m.loadMenu(categories);

        m.addMenuItem("'q' to Quit"); 
        System.out.println("The following org.rit.swen440.presentation.categories are available");
        m.printMenu();
        String result;
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

        List<Product> productList = Product.getProductsInCategory(currentCategoryName);
        List<String> itemList = new ArrayList<>();
        Product currentProduct = null;

        for(Product product: productList){
            itemList.add(product.toString());
        }

        System.out.println("");

        m.loadMenu(itemList);
        m.addMenuItem("'q' to Quit");
        System.out.println("The following items are available");
        m.printMenu();
        String result = m.getSelection();

        try {
            int iSel = Integer.parseInt(result); //Item selected
            currentProduct = productList.get(iSel);

            System.out.println("You want item from the catalog: " + currentProduct.getName());
        }
        catch (Exception e) {
            result = "q";
        }
        if (result.equalsIgnoreCase("q"))
            currentLevel--;
        else {
            OrderQty(currentProduct);
        }
    }

    //Order Level
    private void OrderQty(Product currentProduct) {
        System.out.println("Please select a quantity");

        System.out.println(currentProduct.getName() + " availability: " + currentProduct.getCount());
        System.out.print(":");
        menu m = new menu();
        int result = Integer.parseInt(m.getSelection());

        if (result > currentProduct.getCount()) {
            System.out.println("There are not that many available to order.");
        } else {
            TransactionProduct transactionProduct = new TransactionProduct(currentProduct, currentProduct.getPrice(), result);
            List<TransactionProduct> transactionProducts = new ArrayList<TransactionProduct>();
            transactionProducts.add(transactionProduct);

            Transaction.createTransaction(user, transactionProducts);

            System.out.println("You ordered:" + result);
        }
    }
}