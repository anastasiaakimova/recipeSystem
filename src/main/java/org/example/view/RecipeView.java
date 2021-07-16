package org.example.view;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.service.impl.RecipeIngredientServiceImpl;
import org.example.service.impl.RecipeServiceImpl;

import java.sql.SQLException;
import java.util.*;

public class RecipeView {
    private MainView mainView;

    private RecipeServiceImpl recipeService = new RecipeServiceImpl();
    RecipeIngredientServiceImpl recipeIngredientService = new RecipeIngredientServiceImpl();


    public RecipeView() throws SQLException {
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("---------------------------------------");
                System.out.println("Choose number to do operation, please: ");
                System.out.println("1. Show all recipe list");
                System.out.println("2. Find recipe by name");
                System.out.println("3. Find recipe by set of ingredients");
                System.out.println("4. Add recipe ");
                System.out.println("5. Delete recipe ");
                System.out.println("6. Back");
                System.out.println("---------------------------------------");
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
        } catch (InputMismatchException | SQLException e) {
            System.out.println("You entered something wrong! ");
            run();
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

        Recipe recipe = recipeService.findByName(name);

        System.out.println("You can edit description or delete some ingredients");
        System.out.println("1. Edit descriptions ");
        System.out.println("2. Delete ingredients ");
        System.out.println("3. Back ");

        while (true) {
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Enter new description, please: ");
                    String newDescription = scanner1.nextLine();
                    recipe.setDescription(newDescription);
                    recipeService.update(recipe);
                    break;
                case 2:
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Enter ingredients which you want to delete, please: ");
                    //thinking

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

    private void findRecipeByIngredientsSet() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Set ingredientsSet = new HashSet<Ingredient>();

        boolean go = true;
        while (go) {
            System.out.println("Enter recipe's ingredient, please: ");
            System.out.println("If you don't want to add ingredient enter no. ");
            go = isGo(scanner, ingredientsSet, go);
        }
        recipeService.findByIngredientsSet(ingredientsSet);
    }

    private boolean isGo(Scanner scanner, Set ingredientsSet, boolean go) {
        String yesOrNo = scanner.nextLine();
        switch (yesOrNo.toLowerCase()) {
            case "no":
                System.out.println("You choose do not add new ingredient");
                go = false;
                break;
            default:
                System.out.println("Enter name of ingredient :");
                String ingredient = scanner.nextLine();
                ingredientsSet.add(ingredient);
                break;
        }
        return go;
    }

    private void addRecipe() throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            Recipe recipe = new Recipe();
            RecipeIngredient recipeIngredient = new RecipeIngredient();

            System.out.println("Enter recipe's name, please: ");
            String name = scanner.nextLine();
            recipe.setName(name);

            System.out.println("Enter recipe's description, please: ");
            String description = scanner.nextLine();
            recipe.setDescription(description);

            Set ingredientsSet = new HashSet<Ingredient>();

            boolean go = true;
            while (go) {
                System.out.println("Enter recipe's ingredient, please: ");
                System.out.println("If you don't want to add ingredient enter no: ");
                go = isGo(scanner, ingredientsSet, go);
            }
            recipeIngredient.setRecipe(recipe);
            recipeIngredient.setIngredient(ingredientsSet);
            recipeIngredientService.save(recipeIngredient);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteRecipe() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name which you want to delete, please: ");
        String name = scanner.nextLine();
        recipeService.remove(name);
    }

    private void showMainMenu() throws SQLException {
        MainView.getInstance().run();
    }
}
