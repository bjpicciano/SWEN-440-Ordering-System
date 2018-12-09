package org.rit.swen440.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Transaction {
    private int id;
    private User client;
    private List<TransactionProduct> transactionProducts;
    private Date dateOrdered;
    private Optional<Date> dateShipped;
    private Optional<Date> dateReceived;

    public static boolean createTransaction(User client, List<TransactionProduct> products) {
        return false;
    }

    public static Transaction getTransactionById(int id) {
        Transaction transaction = new Transaction();
        return transaction;
    }

    public static List<Transaction> queryAuditLog(String predicate) {
        List<Transaction> transactions = new ArrayList<>();
        return transactions;
    }
}
