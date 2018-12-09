package org.rit.swen440.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int id;
    private String email;

    public User() {
    }

    public User(int id, String email) {
        this.id = id;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
