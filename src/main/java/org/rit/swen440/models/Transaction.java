package org.rit.swen440.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Transaction {
    private int id;
    private User client;
    private List<TransactionProduct> transactionProducts;
    private Date dateOrdered;
    private Date dateShipped;
    private Date dateReceived;

    public Transaction() {
    }

    public Transaction(int id, User client, List<TransactionProduct> transactionProducts, String dateOrdered, String dateShipped, String dateReceived) {
        this.id = id;
        this.client = client;
        this.transactionProducts = transactionProducts;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.dateOrdered = formatter.parse(dateOrdered);
            if (dateShipped != null) this.dateShipped = formatter.parse(dateShipped);
            if (dateReceived != null) this.dateReceived = formatter.parse(dateReceived);
        } catch (ParseException e) {
            e.getMessage();
        }
    }

    public static boolean createTransaction(User client, List<TransactionProduct> products) {
        return false;
    }

    public static Transaction getTransactionById(int id) {
        Transaction transaction = new Transaction();
        List<TransactionProduct> transactionProducts = TransactionProduct.getTransactionProductsByTransactionId(id);

        try {
            ResultSet rs = Database.query(
                    "SELECT\n" +
                    "       id,\n" +
                    "       date_ordered,\n" +
                    "       date_shipped,\n" +
                    "       date_received,\n" +
                    "       user_id\n" +
                    "FROM transactions\n" +
                    "WHERE id = " + id);

            String dateOrdered = rs.getString("date_ordered");
            String dateShipped = rs.getString("date_shipped");
            String dateReceived = rs.getString("date_received");

            int userId = rs.getInt("user_id");
            User user = User.getUserById(userId);
            transaction = new Transaction(id, user, transactionProducts, dateOrdered, dateShipped, dateReceived);

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transaction;
    }

    public static List<Transaction> queryAuditLog(String predicate) {
        List<Transaction> transactions = new ArrayList<>();
        return transactions;
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
