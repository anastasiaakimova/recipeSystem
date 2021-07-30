package org.example.view;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.service.impl.IngredientServiceImpl;
import org.example.service.impl.RecipeServiceImpl;

import java.sql.SQLException;
import java.util.*;

public class RecipeView {
    private RecipeServiceImpl recipeService = new RecipeServiceImpl();
    private IngredientServiceImpl ingredientService = new IngredientServiceImpl();

    public RecipeView() {
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("---------------------------------------");
                System.out.println("Choose number to do operation, please: ");
                System.out.println("1. Show all recipe list");
                System.out.println("2. Update recipe ");
                System.out.println("3. Find recipe by set of ingredients");
                System.out.println("4. Add recipe ");
                System.out.println("5. Delete recipe ");
                System.out.println("6. Sort recipes by calories ");
                System.out.println("7. Sort recipes by range calories ");
                System.out.println("8. Back");
                System.out.println("---------------------------------------");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        showAllRecipes();
                        break;
                    case 2:
                        updateRecipe();
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
                        sortByCalories();
                        break;
                    case 7:
                        sortByRangeOfCalories();
                        break;
                    case 8:
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
        recipes.forEach(recipe -> System.out.println(recipe.toString()));
    }

    private void updateRecipe() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name, please: ");
        String name = scanner.nextLine();
        Ingredient dbIngredient = new Ingredient();
        RecipeIngredient ingredient = new RecipeIngredient();

        try {
            Recipe recipe = recipeService.findByName(name);
            if (recipe != null) {
                System.out.println(recipe);

                while (true) {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("You can edit name and description or add and delete some ingredients");
                    System.out.println("1. Edit name ");
                    System.out.println("2. Edit descriptions ");
                    System.out.println("3. Delete ingredients ");
                    System.out.println("4. Add ingredients ");
                    System.out.println("5. Back ");
                    int number = scanner.nextInt();
                    switch (number) {
                        case 1:
                            System.out.println("Enter new name, please: ");
                            scanner.skip("\n");
                            String newName = scanner.nextLine();
                            recipe.setName(newName);

                            recipeService.updateName(recipe);
                            System.out.println("Your recipe was successfully edited!");
                            break;
                        case 2:
                            System.out.println("Enter new description, please: ");
                            scanner.skip("\n");
                            String newDescription = scanner.nextLine();
                            recipe.setDescription(newDescription);

                            recipeService.updateDescription(recipe);
                            System.out.println("Your recipe was successfully edited!");
                            break;
                        case 3:
                            System.out.println("Enter ingredient's name which you want to delete, please: ");
                            scanner.skip("\n");
                            String deleteName = scanner.nextLine();

                            dbIngredient = ingredientService.getByName(deleteName);
                            ingredient.setId(dbIngredient.getId());
                            recipeService.deleteIngredient(ingredient);
                            System.out.println("Your recipe was successfully edited!");

                            Recipe newRecipe = recipeService.findByName(name);
                            System.out.println(newRecipe);
                            break;
                        case 4:
                            System.out.println("Enter ingredients which you want to add, please: ");
                            System.out.println("-------------------------------------------------------------------");
                            System.out.println("You can add only this ingredients: " + ingredientService.getAll());
                            System.out.println("-------------------------------------------------------------------");

                            scanner.skip("\n");
                            String ingName = scanner.nextLine();
                            ingredient.setName(ingName);

                            dbIngredient = ingredientService.getByName(ingName);
                            ingredient.setId(dbIngredient.getId());
                            ingredient.setCalories(dbIngredient.getCalories());

                            System.out.println("Enter required amount, please: ");
                            int requiredAmount = scanner.nextInt();
                            ingredient.setRequiredAmount(requiredAmount);

                            List<RecipeIngredient> ingredients = new LinkedList<>();

                            ingredients.add(ingredient);

                            recipeService.updateIngredients(recipe, ingredients);
                            System.out.println(recipeService.findByName(name));
                            System.out.println("Your recipe was successfully edited!");
                            break;
                        case 5:
                            run();
                            break;
                        default:
                            System.out.println("Wrong number!");
                            System.out.println("Enter number from 1 to 4, please");
                    }
                }

            } else {
                System.out.println("This name doesn't exist");
            }
        } catch (NullPointerException e) {

            if (!ingredient.equals(dbIngredient)) {
                System.out.println("This ingredient doesn't exist! ");
            } else
                System.out.println("There is no name");
        }

    }

    private void findRecipeByIngredientsSet() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        List<RecipeIngredient> ingredients = new LinkedList<>();
        Ingredient dbIngredient;
        boolean go = true;
        while (go) {
            System.out.println("1. Enter ingredient ");
            System.out.println("2. Back ");

            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    try {
                        RecipeIngredient ingredient = new RecipeIngredient();

                        System.out.println("Ingredients which you can enter: " + ingredientService.getAll());

                        System.out.println("Enter name of ingredient :");
                        scanner.skip("\n");
                        String name = scanner.nextLine();
                        ingredient.setName(name);

                        dbIngredient = ingredientService.getByName(name);
                        ingredient.setId(dbIngredient.getId());
                        ingredient.setCalories(dbIngredient.getCalories());

                        System.out.println("Enter required amount of ingredient :");
                        int requiredAmount = scanner.nextInt();
                        ingredient.setRequiredAmount(requiredAmount);

                        ingredients.add(ingredient);
                        break;


                    } catch (NullPointerException e) {
                        System.out.println("This name doesn't exist! Try another: ");
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("This is invalid meaning! Try another: ");
                        break;
                    }
                case 2:
                    System.out.println("You choose do not add new ingredient");
                    go = false;
                    break;
                default:
                    System.out.println("Wrong number!");
                    System.out.println("Enter number from 1 to 2, please");
                    break;
            }
        }
        List<Recipe> recipes = recipeService.findByIngredientsSet(ingredients);
        if (recipes.isEmpty()) {
            System.out.println("Recipes doesn't exist!");
        } else
            System.out.println("List of recipes: ");
        recipes.forEach(recipe -> System.out.println(recipe.toString()));
    }

    //сортировка по калорийности
    private void sortByCalories() throws SQLException {
        List<Recipe> recipes = recipeService.sortByCalories();

        recipes.forEach(recipe -> System.out.println(recipe.toString()));
    }

    //сортировка по диапазону
    private void sortByRangeOfCalories() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter min number of range: ");
        Double min = scanner.nextDouble();

        System.out.println("Enter max number of range: ");
        Double max = scanner.nextDouble();

        List<Recipe> recipes = recipeService.sortByRangeCalories(min, max);
        recipes.forEach(recipe -> System.out.println(recipe.toString()));

    }

    // добавление рецепта работает
    private void addRecipe() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Recipe recipe = new Recipe();
        Ingredient dbIngredient = new Ingredient();
        try {
            System.out.println("Enter recipe's name, please: ");
            String name = scanner.nextLine();
            recipe.setName(name);

            System.out.println("Enter recipe's description, please: ");
            String description = scanner.nextLine();
            recipe.setDescription(description);
            System.out.println("Ingredients, which you can add: " + ingredientService.getAll());

            List<RecipeIngredient> ingredients = new LinkedList<>();

            boolean go = true;
            while (go) {
                System.out.println("1. Enter recipe's ingredient ");
                System.out.println("2. Back");
                int yesOrNo = scanner.nextInt();
                switch (yesOrNo) {
                    case 1:
                        RecipeIngredient ingredient = new RecipeIngredient();
                        try {
                            System.out.println("Enter ingredient's name: ");
                            scanner.skip("\n");
                            String ingName = scanner.nextLine();
                            ingredient.setName(ingName);

                            dbIngredient = ingredientService.getByName(ingName);
                            ingredient.setId(dbIngredient.getId());

                            System.out.println("Enter required amount, please: ");
                            int requiredAmount = scanner.nextInt();
                            ingredient.setRequiredAmount(requiredAmount);

                            ingredients.add(ingredient);
                            recipe.setIngredients(ingredients);

                            System.out.println("Your ingredient was successfully added! ");
                        } catch (NullPointerException e) {
                            if (ingredient.equals(dbIngredient)) {
                                System.out.println("This ingredient doesn't exist! ");
                            }
                        }
                        break;
                    case 2:
                        System.out.println("You choose do not add an ingredient");
                        go = false;
                        break;
                    default:
                        System.out.println("Wrong number");
                        System.out.println("Enter number from 1 to 2, please");
                }
            }

            recipeService.save(recipe);
        } catch (InputMismatchException | NullPointerException e) {
            System.out.println("There is no name");
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
