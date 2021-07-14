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
    @Override
    public Ingredient save(Ingredient ingredient) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "INSERT INTO ingredient (name, calories) VALUES ( ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ingredient.getName());
        preparedStatement.setFloat(2, ingredient.getCalories());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return ingredient;
    }

    // метод изменяет поля (имя и калории) ингридиента по заданному name
    @Override
    public Ingredient update(Ingredient ingredient) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "UPDATE ingredient SET name = ?, calories = ? WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ingredient.getName());
        preparedStatement.setFloat(2, ingredient.getCalories());
        preparedStatement.setString(3, ingredient.getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return ingredient;
    }

    // метод выводит все поля всех рецептов
    @Override
    public List<Ingredient> getAll() throws SQLException {
        Connection connection = DbConnection.getConnection();
        List<Ingredient> ingredients = new ArrayList<>();
        String query = "SELECT * FROM ingredient";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
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
        Connection connection = DbConnection.getConnection();
        String query = "SELECT * FROM ingredient WHERE id = ?";
        Ingredient ingredient = new Ingredient();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Ingredient newIngredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("calories"));
            ingredient.setId(id);
            ingredient = newIngredient;
        }
        preparedStatement.close();
        connection.close();
        return ingredient;
    }

    // удаление ингредиента по заданному name
    @Override
    public void deleteByName(String name) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "DELETE * FROM ingredient WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public Ingredient getByName(String name) throws SQLException{
        Connection connection = DbConnection.getConnection();
        String query = "SELECT * FROM ingredient WHERE name = ?";
        Ingredient ingredient = new Ingredient();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ingredient newIngredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("calories"));
            ingredient.setName(name);
            ingredient = newIngredient;
        }
        preparedStatement.close();
        connection.close();
        return ingredient;
    }
}
