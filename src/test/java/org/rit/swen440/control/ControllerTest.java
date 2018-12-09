package org.rit.swen440.control;

import org.junit.Test;
import org.rit.swen440.models.Category;
import org.rit.swen440.models.User;

import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller controller = new Controller();
    @Test
    public void getTransactionsBetweenDates() {

    }

    @Test
    public void getTopAndBottomSoldProducts() {

    }

    @Test
    public void login() {
        User user = controller.login("admin@oos.com", "123");
        if (user == null)
            fail();

        User notUser = controller.login("notauser@fakeemail.com", "notapassword");
        if (notUser != null)
            fail();
    }

    @Test
    public void getAllCategories() {
        List<Category> categories = controller.getAllCategories();

        if (categories.isEmpty())
            fail();
    }

    @Test
    public void getProductsInCategory() {

    }

    @Test
    public void createTransaction() {

    }
}