package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
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

    @Override
    public Ingredient save(Ingredient ingredient) throws SQLException {
        return null;
    }

    @Override
    public Ingredient update(Ingredient ingredient) throws SQLException {
        return null;
    }

    @Override
    public List<Ingredient> getAll() throws SQLException {
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        String query = "SELECT * FROM ingredients";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Ingredient ingredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("calories"));
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @Override
    public Ingredient getById(Integer integer) throws SQLException {
        String query = "SELECT * FROM ingredients WHERE id = ?";

        Ingredient ingredient = null;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Ingredient newIngredient = new Ingredient(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getFloat("calories"));
            ingredient.setId(id);
        }
        return ingredient;
    }

    @Override
    public void deleteById(Integer integer) throws SQLException {

    }
}
