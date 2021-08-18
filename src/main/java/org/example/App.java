package org.example;

import org.example.view.MainView;

import java.sql.SQLException;

/**
 * The main class which start the application
 *
 * @author Anastasia Akimova
 * @version 1.0
 */

public class App {
    public static void main(String[] args) throws SQLException {
        MainView.getInstance().run();
    }
}