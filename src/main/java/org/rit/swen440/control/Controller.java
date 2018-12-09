package org.rit.swen440.control;


import org.rit.swen440.models.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Controls access to data, on start-up scans directories and builds internal
 * representation of categories and items within each category.  Isolates the
 * categories and products from information on the underlying file system.
 */
public class Controller {
  private Set<Category> categories = new HashSet<>();

  public  enum PRODUCT_FIELD {
    NAME,
    DESCRIPTION,
    COST,
    INVENTORY
  };

  public Controller(String directory) {

  }

  public Optional<Category> getAllCategories() {
    return null;
    //TODO
  }

  public Optional<Product> getProductBySKU(int sku) {
    return null;
    //TODO
  }

  public Optional<Product> getAllProducts() {
    return null;
    //TODO
  }

  public Optional<Product> getProductsInCategory(String categoryName) {
    return null;
    //TODO
  }

  public boolean createTransaction(User client, List<Product> products) {
    return false;
    //TODO
  }

  public Transaction getTransactionById(int id) {
    return null;
    //TODO
  }

  public Optional<Transaction> queryAudiLog(String predicate) {
    return null;
    //TODO
  }
}
