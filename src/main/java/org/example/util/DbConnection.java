package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static String username = "root";
    private static String password = "root";
    private static String databaseURL = "jdbc:postgresql://localhost:5433/";
    private static String driver = "org.postgresql.Driver";

    public static Connection getConnection() throws SQLException {

        Connection myConnection = null;
        try {
            myConnection = DriverManager.getConnection(databaseURL, username, password);
            Class.forName(driver);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        return myConnection;
    }
}