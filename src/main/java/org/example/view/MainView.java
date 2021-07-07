package org.example.view;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {

    private static MainView view;
    private RecipeView recipeView;

    public MainView() {
        recipeView = new RecipeView();
    }

    public static MainView getInstance() {
        if (view == null) {
            view = new MainView();
        }
        return view;
    }

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean go = true;
        System.out.println("Please select a number to select an option: ");
        System.out.println("1. Recipes");
        System.out.println("2. Ingredients");
        System.out.println("3. Exit");
        while (go) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    showRecipeMenu();
                    break;
                case 2:
                    break;
                case 3:
                    go = false;
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 4, please");

            }
        }
    }

    public void showRecipeMenu() throws SQLException{
        recipeView.run();
    }
}