package com.efrei.demospring.oldway.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}