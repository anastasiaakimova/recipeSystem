package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static String username = "root";
    private static String password = "root";
    private static String databaseURL = "jdbc:postgresql://localhost:5433/recipeSystem";
    private static String driver = "org.postgresql.Driver";

    public static Connection getConnection() {

        Connection myConnection = null;
        try {
            myConnection = DriverManager.getConnection(databaseURL, username, password);
            Class.forName(driver);
            System.out.println("Connected to the PostgreSQL server successfully.");
            return myConnection;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        return myConnection;
    }
}