package com.hotelreservation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static DBConnectionManager instance;

    private static final String URL =
            "jdbc:mysql://localhost:3308/ocen_view_resort_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123";

    // Private constructor (Singleton)
    private DBConnectionManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    // Singleton accessor
    public static DBConnectionManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionManager();
        }
        return instance;
    }

    // FACTORY METHOD: returns a NEW connection every time
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
