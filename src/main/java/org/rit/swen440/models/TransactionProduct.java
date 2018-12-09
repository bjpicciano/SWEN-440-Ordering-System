package org.rit.swen440.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionProduct {
    private Product product;
    private float purchasePrice;
    private int quantity;

    public TransactionProduct(Product product, float purchasePrice, int quantity) {
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
    }

    public static boolean createTransactionProduct() {
        try {
            ResultSet rs = Database.query(
                "INSERT INTO transaction_product (transaction_id,product_sku,purchase_price,quantity)\n" +
                    "VALUES (1,2,5.00,10)"
            );

            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    protected static List<TransactionProduct> getTransactionProductsByTransactionId(int transactionId) {
        List<TransactionProduct> transactionProducts = new ArrayList<>();

        try {
            ResultSet rs = Database.query(
                    "SELECT\n" +
                    "       transaction_id,\n" +
                    "       product_sku,\n" +
                    "       purchase_price,\n" +
                    "       quantity\n" +
                    "FROM transaction_product\n" +
                    "WHERE transaction_id = " + transactionId
            );
            while (rs.next()) {
                int sku = rs.getInt("product_sku");
                Product product = Product.getProductBySKU(sku);

                float purchasePrice = rs.getFloat("purchase_price");
                int quantity = rs.getInt("quantity");

                transactionProducts.add(new TransactionProduct(product, purchasePrice, quantity));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transactionProducts;
    }

    @Override
    public String toString() {
        return "TransactionProduct{" +
                "product=" + product +
                ", purchasePrice=" + purchasePrice +
                ", quantity=" + quantity +
                '}';
    }
}
