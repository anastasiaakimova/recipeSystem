package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.repository.RecipeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RecipeRepoImpl implements RecipeRepository {

    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    // методы выводит все поля всех рецептов
    @Override
    public List<Recipe> getAll() throws SQLException {
        List<Recipe> recipes = new ArrayList<Recipe>();
        String query = "SELECT * FROM recipe";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
            recipes.add(recipe);
        }
        return recipes;
    }

    // добавление рецепта
    @Override
    public Recipe save(Recipe recipe) {
        return null;
    }

    // редактироание рецепта
    @Override
    public Recipe update(Recipe recipe) {
        return null;
    }

    // метод выводит все поля рецептов по заданному id
    //дописать
    @Override
    public Recipe getById(Integer id) throws SQLException {
        String query = "SELECT * FROM recipe WHERE id = ?";

        Recipe recipe = null;
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Recipe newRecipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
            recipe.setId(id);
        }
        return recipe;
    }

    // удаление рецепта
    @Override
    public void deleteById(Integer id) throws SQLException {
        String query = "DELETE * FROM recipe WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    // метода выводит все поля рецепта по заданному имени
    @Override
    public Recipe getByName(String name) throws SQLException {
        String query = "SELECT * FROM recipe WHERE name = ?";
        List<Recipe> recipes = new ArrayList<Recipe>();

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
            recipes.add(recipe);
        }
        //    Map<String, Recipe> map = new HashMap<>();

        return (Recipe) recipes;
    }

    //метод показывает все ингредиенты заданного рецепта по id

    @Override
    public Recipe viewIngredients() throws SQLException {

        //подправить
        String query = "SELECT recipe.id, recipe.name " +
                "AS recipeName, recipe.description, i.name " +
                "AS ingredients, ri.requireamount " +
                "AS requiredAmount FROM recipe\n" +
                "    RIGHT JOIN recipe_ingredients ri on recipe.id = ri.id_recipe\n" +
                "    LEFT JOIN ingredients i on ri.id_ingredients = i.id;";

        Map<String, Recipe> map = new HashMap<>();

        while (resultSet.next()) {
            if (map.containsKey(resultSet.getString("recipeName"))) {
                Ingredient ingredient = new Ingredient();
    //            map.get(resultSet.getString("recipeName")).getRecipeIngredients().add(ingredient);
            } else {
                Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
                Ingredient ingredient = new Ingredient();
     //           recipe.getRecipeIngredients().add(ingredient);
                map.put(recipe.getName(), recipe);
            }
        }
   //     map
        return null;
    }

    // метод показывает все возможные рецепты по заданному сету ингредиентов
    @Override
    public List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) throws SQLException {
        return null;
    }
}
