package org.rit.swen440.control;

import org.junit.Test;
import org.rit.swen440.models.Category;
import org.rit.swen440.models.Product;
import org.rit.swen440.models.Transaction;
import org.rit.swen440.models.User;

import java.util.List;

import static org.junit.Assert.fail;

public class ControllerTest {
    private Controller controller = new Controller();

    @Test
    public void getTransactionsBetweenDates() {
        List<Transaction> transactions = controller.getTransactionsBetweenDates("2018-12-01", "2018-12-30");
        if (transactions.isEmpty())
            fail();
    }

    @Test
    public void getTopAndBottomSoldProducts() {
        List<Product> products = controller.getTopAndBottomSoldProducts("2018-12");

        if (products.size() > 2)
            fail();
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
        if (controller.getProductsInCategory("toy").isEmpty())
            fail();
    }
}