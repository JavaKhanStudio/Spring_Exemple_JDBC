package com.efrei.demospring.oldway.repo;

import java.sql.*;

public class DatabaseUtil {

    private static final String URL = "jdbc:h2:mem:public";
    private static final String USER = "sa";
    private static final String PASSWORD = "password";

    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    // Must have something like
    // pstmt = conn.prepareStatement(statement, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    public static void printRaw(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            String columnName = rsmd.getColumnName(i);
            System.out.print(columnName + "\t");
        }
        System.out.println();

        // Iterate through the ResultSet and print data
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                // Retrieve column value and print it
                String columnValue = rs.getString(i);
                System.out.print(columnValue + "\t");
            }
            System.out.println(); // Move to the next line after printing a row
        }

        rs.beforeFirst(); // Go back at the start
    }
}