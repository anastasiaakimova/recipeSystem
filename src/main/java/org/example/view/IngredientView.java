package org.example.view;

import org.example.entity.Ingredient;
import org.example.service.impl.IngredientServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class IngredientView {
    private IngredientServiceImpl ingredientService = new IngredientServiceImpl();
    MainView mainView;

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose number to do operation, please: ");
        System.out.println("1. Show all ingredients");
        System.out.println("2. Add new ingredient");
        System.out.println("3. Update ingredient");
        System.out.println("4. Delete ingredient");
        System.out.println("5. Back ");
        while (true) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    showAllIngredients();
                    break;
                case 2:
                    addNewIngredient();
                    break;
                case 3:
                    updateIngredient();
                    break;
                case 4:
                    deleteIngredient();
                    break;
                case 5:
                    showMainMenu();
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 5, please");
            }
        }
    }

    private void showMainMenu() throws SQLException {
        MainView.getInstance().run();
    }

    private void deleteIngredient() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of ingredient which you want to delete, please: ");
        String name = scanner.nextLine();
        ingredientService.remove(name);
    }

    private void updateIngredient() throws SQLException {
    }

    private void addNewIngredient() throws SQLException {
    }

    private void showAllIngredients() throws SQLException {
        List<Ingredient> ingredients = ingredientService.getAll();
        System.out.println("List of ingredients: ");
        ingredients.forEach(ingredient -> System.out.println(ingredient.toString()));
    }
}