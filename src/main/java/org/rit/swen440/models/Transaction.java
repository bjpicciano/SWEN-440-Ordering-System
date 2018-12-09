package org.rit.swen440.models;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Transaction {
    private int id;
    //private Client client;
    private List<TransactionProduct> transactionProducts;
    private Date dateOrdered;
    private Optional<Date> dateShipped;
    private Optional<Date> dateReceived;
}
