package org.example;


import org.example.entity.Recipe;
import org.example.service.RecipeService;
import org.example.service.impl.RecipeServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App {

    private RecipeServiceImpl recipeService = new RecipeServiceImpl();

    private void showAllRecipes() throws SQLException {
        List<Recipe> recipes = recipeService.getAll();
        System.out.println("List of all recipes: ");
        recipes.forEach(recipe1 -> System.out.println(recipe1.toString()));
    }

    private void findRecipeByName() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name, please: ");
        String name = scanner.nextLine();

        // проверка на null
        try {
            if (recipeService.findByName(name) != null) {
                System.out.println(recipeService.findByName(name).toString());
            } else {
                System.out.println("This name doesn't exist");
            }
        } catch (NullPointerException e) {
            System.out.println("There is no name");
        }
        findRecipeByName();

        System.out.println("You can edit description or delete some ingredients");
        System.out.println("1. Edit descriptions ");
        System.out.println("2. Delete ingredients ");
        System.out.println("3. Back ");
        boolean go = true;
        while (go) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    go = false;
                    break;

                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 3, please");

            }
        }
    }

    private void findRecipeByIngredientsSet() {
    }

    public static void main(String[] args) throws SQLException {

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

   /*     Scanner scanner = new Scanner(System.in);
        boolean go = true;

        System.out.println("Choose number to do operation, please: ");
        System.out.println("1. Show all recipe list");
        System.out.println("2. Find recipe by name");
        System.out.println("3. Find recipe by set of ingredients");
        System.out.println("4. End");
        while (go) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    showAllRecipes();
                    break;
                case 2:
                    findRecipeByName();
                    break;
                case 3:
                    findRecipeByIngredientsSet();
                    break;
                case 4:
                    go = false;
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 4, please");

            }
        }*/
        }
    }
}
