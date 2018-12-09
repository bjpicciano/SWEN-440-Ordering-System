package org.rit.swen440.models;

public class TransactionProduct {
    private Product product;
    private float purchasePrice;
    private int quantity;

    public TransactionProduct(Product product, float purchasePrice, int quantity) {
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
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
