package org.example.view;

import org.example.entity.Ingredient;
import org.example.service.impl.IngredientServiceImpl;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * The class view of ingredient's menu and methods.
 */
public class IngredientView {
    private IngredientServiceImpl ingredientService = new IngredientServiceImpl();

    /**
     * The method shows main menu of ingredients functionality
     *
     * @throws SQLException
     */
    public void run() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("---------------------------------------");
                System.out.println("Choose number to do operation, please: ");
                System.out.println("1. Show all ingredients");
                System.out.println("2. Add new ingredient");
                System.out.println("3. Update ingredient");
                System.out.println("4. Delete ingredient");
                System.out.println("5. Back ");
                System.out.println("---------------------------------------");
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
        } catch (InputMismatchException | SQLException e) {
            System.out.println("You entered something wrong! ");
            run();
        }
    }

    /**
     * The method goes over to main menu.
     *
     * @throws SQLException
     */
    private void showMainMenu() throws SQLException {
        MainView.getInstance().run();
    }

    /**
     * The method delete ingredient.
     *
     * @throws SQLException
     */
    private void deleteIngredient() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of ingredient which you want to delete, please: ");
        String name = scanner.nextLine();
        ingredientService.remove(name);
    }

    /**
     * The method update ingredient.
     *
     * @throws SQLException
     */

    private void updateIngredient() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ingredient's name, which you want to edit, please: ");
        String name = scanner.nextLine();
        Ingredient ingredient = ingredientService.getByName(name);

        System.out.println("Enter new ingredient's name, please: ");
        String newName = scanner.nextLine();
        ingredient.setName(newName);

        System.out.println("Enter new ingredient's calories, please: ");
        Double calories = scanner.nextDouble();
        ingredient.setCalories(calories);
        ingredientService.update(ingredient);
    }

    /**
     * The method add new ingredient.
     */
    private void addNewIngredient() {
        try {
            Scanner scanner = new Scanner(System.in);
            Ingredient ingredient = new Ingredient();

            System.out.println("Enter ingredient's name, please: ");
            String name = scanner.nextLine();
            ingredient.setName(name);
            System.out.println("Enter ingredient's calories, please: ");
            Double calories = scanner.nextDouble();
            ingredient.setCalories(calories);

            ingredientService.save(ingredient);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method show list of ingredients with their calories.
     *
     * @throws SQLException
     */
    private void showAllIngredients() throws SQLException {
        List<Ingredient> ingredients = ingredientService.getAll();
        System.out.println("List of ingredients: ");
        ingredients.forEach(ingredient -> System.out.println(ingredient.toString()));
    }
}