package org.example;


import org.example.entity.Recipe;
import org.example.service.impl.RecipeServiceImpl;
import org.example.view.MainView;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {



    public static void main(String[] args) throws SQLException {
        MainView.getInstance().run();
    }


}

