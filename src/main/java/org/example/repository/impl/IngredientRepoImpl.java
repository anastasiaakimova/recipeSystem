package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.repository.IngredientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientRepoImpl implements IngredientRepository {

    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // метод добавляет новый ингредиент
    @Override
    public Ingredient save(Ingredient ingredient) throws SQLException {
        String query = "INSERT INTO ingredient (id, name, calories) VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, ingredient.getId());
        preparedStatement.setString(2, ingredient.getName());
        preparedStatement.setFloat(3, ingredient.getCalories());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return ingredient;
    }

    // метод изменяет поля (имя и калории) ингридиента по заданному id
    @Override
    public Ingredient update(Ingredient ingredient) throws SQLException {
        String query = "UPDATE ingredient SET name = ?, calories = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ingredient.getName());
        preparedStatement.setFloat(2, ingredient.getCalories());
        preparedStatement.setInt(3, ingredient.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return ingredient;
    }

    // метод выводит все поля всех рецептов
    @Override
    public List<Ingredient> getAll() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        String query = "SELECT * FROM ingredient";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ingredient ingredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("calories"));
            ingredients.add(ingredient);
        }
        preparedStatement.close();
        connection.close();
        return ingredients;
    }

    // метод выводит все поля ингредиента по заданному id
    @Override
    public Ingredient getById(Integer id) throws SQLException {
        String query = "SELECT * FROM ingredient WHERE id = ?";

        Ingredient ingredient = new Ingredient();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Ingredient newIngredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("calories"));
            ingredient.setId(id);
            ingredient = newIngredient;
        }
        preparedStatement.close();
        connection.close();
        return ingredient;
    }

    // удаление ингредиента

    @Override
    public void deleteByName(String name) throws SQLException {
        String query = "DELETE * FROM ingredient WHERE name = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
