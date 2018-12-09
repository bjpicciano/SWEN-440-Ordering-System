package org.rit.swen440.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Transaction {
    private int id;
    private User client;
    private List<TransactionProduct> transactionProducts;
    private Date dateOrdered;
    private Date dateShipped;
    private Date dateReceived;

    public Transaction(int id, User client, List<TransactionProduct> transactionProducts, String dateOrdered, String dateShipped, String dateReceived) {
        this.id = id;
        this.client = client;
        this.transactionProducts = transactionProducts;

        // Convert String into Dates
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.dateOrdered = formatter.parse(dateOrdered);
            if (dateShipped != null) this.dateShipped = formatter.parse(dateShipped);
            if (dateReceived != null) this.dateReceived = formatter.parse(dateReceived);
        } catch (ParseException e) {
            e.getMessage();
        }
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", client=" + client +
                ", transactionProducts=" + transactionProducts +
                ", dateOrdered=" + dateOrdered +
                ", dateShipped=" + dateShipped +
                ", dateReceived=" + dateReceived +
                '}';
    }
}
