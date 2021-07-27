package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.repository.IngredientRepository;
import org.example.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientRepoImpl implements IngredientRepository {

    // метод добавляет новый ингредиент
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

    // метод изменяет поля (имя и калории) ингридиента по заданному name
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

    // метод выводит все поля всех рецептов
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

    // метод выводит все поля ингредиента по заданному id
    public Ingredient getById(Integer id) {
        Ingredient ingredient = new Ingredient();
        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT * FROM ingredient WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ingredient newIngredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getDouble("calories"));
                ingredient.setId(id);
                ingredient = newIngredient;
            }
        } catch (SQLException e) {
            System.out.println("You wrote something wrong!");
        }
        return ingredient;
    }

    // удаление ингредиента по заданному name
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
