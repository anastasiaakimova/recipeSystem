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
                        //         findRecipeByIngredientsSet();
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
        Map<String, Recipe> recipes = recipeService.getAll();
        recipes.forEach((k, v) -> System.out.println(v));
    }

    private void findRecipeByName() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name, please: ");
        String name = scanner.nextLine();

        try {
            Map<String, Recipe> recipe = recipeService.findByName(name);
            if (!recipe.isEmpty()) {
                System.out.println(recipe.values());

                while (true) {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("You can edit name and description or add and delete some ingredients");
                    System.out.println("1. Edit name and descriptions ");
                    System.out.println("2. Delete ingredients ");
                    System.out.println("3. Add ingredients ");
                    System.out.println("4. Back ");
                    int number = scanner.nextInt();
// доделать
                    List<RecipeIngredient> ingredients = new LinkedList<>();
                    Recipe recipe1 = recipe.get(name);
                    switch (number) {
                        case 1:
                            Scanner scanner1 = new Scanner(System.in);
                            System.out.println("Enter new name, please: ");
                            String newName = scanner1.nextLine();
                            recipe1.setName(newName);

                            System.out.println("Enter new description, please: ");
                            String newDescription = scanner1.nextLine();
                            recipe1.setDescription(newDescription);

                            recipe.replace(name, recipe1);
                            recipeService.update(recipe);
                            break;
                        case 2:

                            //    System.out.println("Enter ingredient's name which you want to delete, please: ");

                            break;
                        case 3:
                            RecipeIngredient ingredient = new RecipeIngredient();
                            Scanner scanner2 = new Scanner(System.in);
                            System.out.println("You can add only this ingredients: " + ingredientService.getAll());
                            System.out.println("-------------------------------------------------------------------");
                            System.out.println("Enter ingredients which you want to add, please: ");
                            String ingName = scanner2.nextLine();
                            ingredient.setName(ingName);

                            System.out.println("Enter required amount, please: ");
                            int requiredAmount = scanner2.nextInt();
                            ingredient.setRequiredAmount(requiredAmount);

                            ingredients.add(ingredient);
                            recipe1.setIngredients(ingredients);

                            recipe.replace(name, recipe1);
                            recipeService.update(recipe);

                            //        System.out.println("Your ingredient was successfully added! ");
                            break;
                        case 4:
                            run();
                            break;
                        default:
                            System.out.println("Wrong number");
                            System.out.println("Enter number from 1 to 4, please");

                    }

                    System.out.println("Your recipe was successfully edited!");
                }

            } else {
                System.out.println("This name doesn't exist");
            }
        } catch (NullPointerException e) {
            System.out.println("There is no name");
        }

    }

//    private void findRecipeByIngredientsSet() throws SQLException {
//        Scanner scanner = new Scanner(System.in);
//        Set ingredientsSet = new HashSet<Ingredient>();
//
//        boolean go = true;
//        while (go) {
//            System.out.println("Enter recipe's ingredient, please: ");
//            System.out.println("If you don't want to add ingredient enter no. ");
//            go = isGo(scanner, ingredientsSet, go);
//        }
//        recipeService.findByIngredientsSet(ingredientsSet);
//    }
//
//    private boolean isGo(Scanner scanner, Set ingredientsSet, boolean go) {
//        String yesOrNo = scanner.nextLine();
//        switch (yesOrNo.toLowerCase()) {
//            case "no":
//                System.out.println("You choose do not add new ingredient");
//                go = false;
//                break;
//            default:
//                System.out.println("Enter name of ingredient :");
//                String ingredient = scanner.nextLine();
//                ingredientsSet.add(ingredient);
//                break;
//        }
//        return go;
//    }

    // добавление рецепта работает
    //дописать добавление ингридиентов
    private void addRecipe() throws SQLException {
        try {
            Scanner scanner = new Scanner(System.in);
            Map<String, Recipe> recipe = new HashMap<>();
            Recipe recipe1 = new Recipe();

            System.out.println("Enter recipe's name, please: ");
            String name = scanner.nextLine();
            recipe1.setName(name);

            System.out.println("Enter recipe's description, please: ");
            String description = scanner.nextLine();
            recipe1.setDescription(description);
            System.out.println("Ingredients, which you can add: " + ingredientService.getAll());

            List<RecipeIngredient> ingredients = new LinkedList<>();

            boolean go = true;
            while (go) {
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("1. Enter recipe's ingredient ");
                System.out.println("2. Back");
                int yesOrNo = scanner.nextInt();
                switch (yesOrNo) {
                    case 1:
                        RecipeIngredient ingredient = new RecipeIngredient();
                        System.out.println("Enter ingredient's name: ");
                        String ingName = scanner1.nextLine();
                        ingredient.setName(ingName);
                        System.out.println("Enter required amount, please: ");

                        int requiredAmount = scanner1.nextInt();
                        ingredient.setRequiredAmount(requiredAmount);
                        ingredients.add(ingredient);

                        recipe1.setIngredients(ingredients);
                        System.out.println("Your ingredient was successfully added! ");
                        break;
                    case 2:
                        System.out.println("You choose do not add new ingredient");
                        go = false;
                        break;
                    default:
                        System.out.println("Wrong number");
                        System.out.println("Enter number from 1 to 2, please");
                }
            }
            recipe.put(name, recipe1);
            recipeService.save(recipe);
            System.out.println("Your recipe was successfully added! ");
        } catch (InputMismatchException e) {
            System.out.println("Something went wrong! " + e.getMessage());
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
