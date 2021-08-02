package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The class is responsible for connecting to the database.
 *
 * @author Anastasia Akimova
 * @version 1.0
 */

public class DbConnection {

    private static String username = "root";
    private static String password = "root";
    private static String databaseURL = "jdbc:postgresql://localhost:5433/recipeSystem";
    private static String driver = "org.postgresql.Driver";

    /**
     * The method is used to connect to database.
     *
     * @return connection
     */
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