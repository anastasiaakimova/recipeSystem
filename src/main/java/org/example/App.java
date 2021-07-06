package org.example;


import org.example.entity.Recipe;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        }
    }

    //дописать всё
    private static void findRecipeByIngredientsSet() {
    }


    private static void findRecipeByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter recipe's name, please: ");
        String name = scanner.nextLine();

        // проверка на null
        try {
            if () {

            } else {
                System.out.println("This name doesn't exist");
            }
        } catch (NullPointerException e) {
            System.out.println("There is no name");
        }
        System.out.println("You can rewrite description or delete some ingredients");
        System.out.println("1. Rewrite descriptions ");
        System.out.println("2. Delete ingredients ");
        System.out.println("3. Exit ");
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

    private static void showAllRecipes() {
        List<Recipe> recipes; // =
        System.out.println("List of all recipes: ");
        recipes.forEach(recipe1 -> System.out.println(recipe1.toString()));
    }

}
