package org.example.view;

import org.example.entity.Recipe;
import org.example.service.impl.RecipeServiceImpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RecipeView {
    private MainView mainView;

    private RecipeServiceImpl recipeService = new RecipeServiceImpl();

    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose number to do operation, please: ");
        System.out.println("1. Show all recipe list");
        System.out.println("2. Find recipe by name");
        System.out.println("3. Find recipe by set of ingredients");
        System.out.println("4. Add recipe ");
        System.out.println("5. Delete recipe ");
        System.out.println("6. Back");
        while (true) {
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
                    addRecipe();
                    break;
                case 5:
                    deleteRecipe();
                    break;
                case 6:
                    showMainMenu();
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 6, please");
            }
        }
    }

    private void showAllRecipes() throws SQLException {
        List<Recipe> recipes = recipeService.getAll();
        System.out.println("List of all recipes: ");
        recipes.forEach(recipe1 -> System.out.println(recipe1.toString()));
    }

    private void findRecipeByName() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name, please: ");
        String name = scanner.nextLine();

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

        while (true) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    run();
                    break;

                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 3, please");
            }
        }
    }

    private void findRecipeByIngredientsSet() {
    }

    private void addRecipe() throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            Recipe recipe = new Recipe();

            System.out.println("Enter recipe's name, please: ");
            String name = scanner.nextLine();
            recipe.setName(name);

            System.out.println("Enter recipe's description, please: ");
            String description = scanner.nextLine();
            recipe.setDescription(description);

            System.out.println("Enter recipe's ingredients, please: ");
            //thinking


            recipeService.save(recipe);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteRecipe() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name which you want to delete, please: ");
        String name = scanner.nextLine();
        Recipe recipe = recipeService.findByName(name);
        recipeService.remove(recipe.getId());
    }

    private void showMainMenu() throws SQLException {
        mainView.getInstance().run();
    }
}
