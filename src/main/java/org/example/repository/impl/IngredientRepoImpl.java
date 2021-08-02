package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is responsible to databases requests for ingredients and response processing.
 *
 * @author Anastasia Akimova
 * @version 1.0
 */
public class IngredientRepoImpl {
    /**
     * The method adding new ingredient to database.
     *
     * @param ingredient This is object which comes from console.
     * @return Ingredient which was added to database.
     */
    public Ingredient save(Ingredient ingredient) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "INSERT INTO ingredient (name, calories) VALUES ( ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredient.getName());
            preparedStatement.setDouble(2, ingredient.getCalories());
            preparedStatement.executeUpdate();
            System.out.println("Your new ingredient was added! ");
        } catch (SQLException e) {
            if (e.getMessage().contains("ingredient_unique_name")) {
                System.out.println("This ingredient already exists! ");
            } else {
                System.out.println("Something went wrong" + e.getMessage());
            }
            System.out.println("Please, choose some other option");
        }
        return ingredient;
    }

    /**
     * The method update ingredient's fields (name, calories) in database.
     *
     * @param ingredient This is object which should be updated.
     * @return Ingredient which was updated in database.
     */
    public Ingredient update(Ingredient ingredient) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "UPDATE ingredient SET name = ?, calories = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ingredient.getName());
            preparedStatement.setDouble(2, ingredient.getCalories());
            preparedStatement.setInt(3, ingredient.getId());
            preparedStatement.executeUpdate();
            System.out.println("Ingredient was updated! ");
        } catch (SQLException e) {
            System.out.println("This ingredient already exists! ");
            System.out.println("Please, choose some other option");
        }
        return ingredient;
    }

    /**
     * The method get all fields of all ingredients from database.
     *
     * @return List of all ingredients which database have.
     */
    public List<Ingredient> getAll() {
        List<Ingredient> ingredients = new ArrayList<>();

        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT * FROM ingredient";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("calories"));
                ingredients.add(ingredient);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return ingredients;
    }

    /**
     * The method delete ingredient from database.
     *
     * @param name This is name of ingredient which should be deleted.
     */
    public void deleteByName(String name) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "DELETE FROM ingredient WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            System.out.println("Ingredient was deleted!");
        } catch (SQLException e) {
            System.out.println("This ingredient used in some recipe");
        }
    }

    /**
     * The method searches by name ingredient from database.
     *
     * @param name This is name of ingredient which should be found.
     * @return ingredient which was found by name.
     */
    public Ingredient getByName(String name) {
        Ingredient ingredient = new Ingredient();
        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT * FROM ingredient WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ingredient newIngredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("calories"));
                ingredient.setName(name);
                ingredient = newIngredient;
            }
        } catch (SQLException e) {
            System.out.println("You wrote something wrong!");
        }
        return ingredient;
    }
}
