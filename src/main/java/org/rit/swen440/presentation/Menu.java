package org.rit.swen440.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu
{
    private List<String> menuList;
    Scanner sc;
    public Menu()
    {
        sc = new Scanner(System.in);
    }

    public void loadMenu(List<String> menuItems)
    {
        menuList = menuItems;
    }

    public void addMenuItem(String item)
    {
        menuList.add(item);
    }

    public void printMenu()
    {
        System.out.println("");
        for (int i = 0; i < menuList.size(); i++)
        {
            if (menuList.get(i).equalsIgnoreCase("\'q\' to Quit")) {
                System.out.println("q: to Quit");
            } else {
                System.out.println(i + ": " + menuList.get(i));
            }
        }
        System.out.println("");
    }

    public String getSelection()
    {
        String result = "x";

        sc.reset();
        result = sc.nextLine();
        return result;
    }
}