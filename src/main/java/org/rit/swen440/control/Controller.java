package org.rit.swen440.control;

import org.rit.swen440.dataLayer.Category;
import org.rit.swen440.dataLayer.Product;
import org.rit.swen440.models.Transaction;
import org.rit.swen440.models.TransactionProduct;
import org.rit.swen440.models.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
