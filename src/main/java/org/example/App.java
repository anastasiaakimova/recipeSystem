package org.example;

import org.example.util.DbConnection;
import org.example.view.MainView;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        DbConnection dbConnection = new DbConnection();
        dbConnection.getConnection();
        MainView.getInstance().run();
    }
}