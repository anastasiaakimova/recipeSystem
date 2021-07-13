package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.repository.RecipeRepository;
import org.example.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RecipeRepoImpl implements RecipeRepository {

    // методы выводит имя описание всех рецептов и их ингридиенты
    @Override
    public List<Recipe> getAll() throws SQLException {
        Connection connection = DbConnection.getConnection();
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT recipe.id, recipe.name AS \"recipeName\", recipe.description, i.name " +
                "AS ingredients, ri.\"requiredAmount\" " +
                "AS \"requiredAmount\" FROM recipe LEFT JOIN recipe_ingredient ri " +
                "ON recipe.id = ri.\"idRecipe\" " +
                "LEFT JOIN ingredient i on ri.\"idIngredient\" = i.id";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("recipeName"), resultSet.getString("description"), Collections.EMPTY_LIST);
            recipes.add(recipe);
        }
        preparedStatement.close();
        connection.close();
        return recipes;
    }

    // добавление рецепта
    // дописать добавление ингридиентов????
    @Override
    public Recipe save(Recipe recipe) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "INSERT INTO recipe (name, description) VALUES(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, recipe.getName());
        preparedStatement.setString(2, recipe.getDescription());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return recipe;
    }

    // редактироание рецепта
    // дописать апдейт ингридиентов тоже
    @Override
    public Recipe update(Recipe recipe) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "UPDATE recipe SET name = ?, description = ? WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, recipe.getName());
        preparedStatement.setString(2, recipe.getDescription());
        preparedStatement.setString(3, recipe.getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return recipe;
    }

    // метод выводит имя описание рецептов и их ингридиенты по заданному id
    @Override
    public Recipe getById(Integer id) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "SELECT recipe.id, recipe.name  AS recipeName, recipe.description, i.name  AS ingredients, ri.\"requiredAmount\"\n" +
                "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri\n" +
                "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i\n" +
                "ON ri.\"idIngredient\" = i.id WHERE recipe.id = ?";
        Recipe recipe = new Recipe();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Recipe newRecipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), (List<RecipeIngredient>) resultSet.getArray("ingredients"));
            newRecipe.setId(id);
            recipe = newRecipe;
        }
        preparedStatement.close();
        connection.close();
        return recipe;
    }

    // удаление рецепта

    @Override
    public void deleteByName(String name) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "DELETE * FROM recipe WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    // метода выводит имя описание рецептов и их ингридиенты по заданному имени
    @Override
    public Recipe getByName(String name) throws SQLException {
        Connection connection = DbConnection.getConnection();
        String query = "SELECT recipe.id, recipe.name AS recipeName, recipe.description, i.name\n" +
                "AS ingredient, ri.\"requiredAmount\"\n" +
                "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri\n" +
                "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i\n" +
                "ON ri.\"idIngredient\" = i.id WHERE recipe.name = ?";
        List<Recipe> recipes = new ArrayList<Recipe>();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), (List<RecipeIngredient>) resultSet.getArray("ingredients"));
            recipes.add(recipe);
        }
        //    Map<String, Recipe> map = new HashMap<>();
        preparedStatement.close();
        connection.close();
        return (Recipe) recipes;
    }

    //метод показывает все ингредиенты заданного рецепта по id

    @Override
    public Recipe viewIngredients() throws SQLException {
        Connection connection = DbConnection.getConnection();

        //подправить
        String query = "SELECT recipe.id, recipe.name " +
                "AS recipeName, recipe.description, i.name " +
                "AS ingredients, ri.requireamount " +
                "AS requiredAmount FROM recipe\n" +
                "RIGHT JOIN recipe_ingredients ri on recipe.id = ri.id_recipe\n" +
                "LEFT JOIN ingredients i on ri.id_ingredients = i.id;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

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
        preparedStatement.close();
        connection.close();

        return null;
    }

    // метод показывает все возможные рецепты по заданному сету ингредиентов
    @Override
    public List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) throws SQLException {
        Connection connection = DbConnection.getConnection();

        return null;
    }
}