package org.rit.swen440.models;

public class User {
    private int id;
    private String email;
    private String accountType;

    public User(int id, String email, String accountType) {
        this.id = id;
        this.email = email;
        this.accountType = accountType;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
