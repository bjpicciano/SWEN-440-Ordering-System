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

    protected static User getUserById(int id) {
        User user = new User();

        try {
            ResultSet rs = Database.query(
                    "SELECT\n" +
                    "       id,\n" +
                    "       email\n" +
                    "FROM user\n" +
                    "WHERE id = " + id);

            String email = rs.getString("email");
            rs.close();

            user = new User(id, email);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
