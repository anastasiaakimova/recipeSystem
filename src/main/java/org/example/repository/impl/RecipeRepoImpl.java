package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.repository.RecipeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeRepoImpl implements RecipeRepository {

    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public List<Recipe> findAll() throws SQLException {
        List<Recipe> recipes = new ArrayList<Recipe>();
        String query = "SELECT id, name FROM recipe";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
            recipes.add(recipe);
        }
        return recipes;
    }

    //дописать
    @Override
    public Recipe findById(Integer id) throws SQLException {
        String query = "SELECT id, name FROM recipe WHERE id = ?";

        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Recipe recipe = null;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Recipe newRecipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getArray());
            recipe.setId(id);
        }
        return recipe;
    }

    @Override
    public Recipe findByName(String name) throws SQLException {
        String query = "SELECT * FROM recipe WHERE name = ?";
        List<Recipe> recipes = new ArrayList<Recipe>();


        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
            recipes.add(recipe);
        }

        return (Recipe) recipes;
    }

    //дописать или переписать запрос
    @Override
    public Recipe viewIngredients() throws SQLException {
        String query = "SELECT id, name FROM recipe " +
                "LEFT JOIN ingredients ON recipeIngredients.idRecipe=recipe.id " +
                "AND recipeIngredients.idIngredient = ingredient.id";

        return null;
    }

    @Override
    public List<Recipe> findRecipesByIngredients(List<Ingredient> ingredient) throws SQLException {
        return null;
    }
}