package org.example.view;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {

    private static MainView view;
    private RecipeView recipeView;
    private IngredientView ingredientView;

    public MainView() throws SQLException {
        recipeView = new RecipeView();
        ingredientView = new IngredientView();
    }

    public static MainView getInstance() throws SQLException {
        if (view == null) {
            view = new MainView();
        }
        return view;
    }

    public void run() throws SQLException {

        System.out.println("Please select a number to select an option: ");
        System.out.println("1. Recipes");
        System.out.println("2. Ingredients");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        while (true) {
            switch (number) {
                case 1:
                    showRecipeMenu();
                    break;
                case 2:
                    showIngredientMenu();
                    break;
                case 3:
                    System.out.println("Thank you! See you! Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 3, please");
            }
        }

    }

    private void showIngredientMenu() throws SQLException {
        ingredientView.run();
    }

    public void showRecipeMenu() throws SQLException {
        recipeView.run();
    }
}