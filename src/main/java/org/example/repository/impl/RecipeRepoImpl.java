package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.repository.RecipeRepository;
import org.example.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RecipeRepoImpl implements RecipeRepository {

    // методы выводит имя описание всех рецептов и их ингридиенты и требуемое количество
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

        Map<String, Recipe> map = new HashMap<>();


        ////// thinking
        while (resultSet.next()) {
            if (map.containsKey(resultSet.getString("recipeName"))) {
                Ingredient ingredient = new Ingredient();
                map.get(resultSet.getString("recipeName")).getIngredients().add(ingredient);
            } else {
                Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("recipeName"), resultSet.getString("description"), Collections.EMPTY_LIST);
                Ingredient ingredient = new Ingredient();
                recipe.getIngredients().add(ingredient);
                map.put(recipe.getName(), recipe);
                ///////
                recipes.add(recipe);
            }
        }

        preparedStatement.close();
        connection.close();
        return recipes;
    }

    // добавление рецепта
    // переписать класс recipeIngredient??? и переписать этот метод под него
    @Override
    public Recipe save(Recipe recipe) throws SQLException {
//        Connection connection = DbConnection.getConnection();
//        connection.setAutoCommit(false);
//        String query = "INSERT INTO recipe (name, description) VALUES(?,?)";
//        String query2 = "INSERT INTO recipe_ingredient (\"idRecipe\", \"idIngredient\", \"requiredAmount\") VALUES( ?, ?, ?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, recipe.getName());
//        preparedStatement.setString(2, recipe.getDescription());
//
//        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
//
//        //проверка на существование ингредиента
//
//        if (idIngredient != null) {
//
//        } else
//
//            preparedStatement1.setInt(1, recipe.getId());
//        preparedStatement1.setInt(2, viewIngredients().getId());
//        preparedStatement1.setInt(3, requiredAmount);
//
//
//        preparedStatement.executeUpdate();
//        preparedStatement1.executeUpdate();
//
//        connection.commit();
//        preparedStatement.close();
//
//        preparedStatement1.close();
//
//        connection.close();
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
            Recipe newRecipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), Collections.EMPTY_LIST);
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
        List<Recipe> recipes = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("recipeName"), resultSet.getString("description"), Collections.EMPTY_LIST);
            recipes.add(recipe);
        }
        //    Map<String, Recipe> map = new HashMap<>();
        preparedStatement.close();
        connection.close();
        return (Recipe) recipes;
    }

    //метод показывает все ингредиенты заданного рецепта по name

    @Override
    public Recipe viewIngredients() throws SQLException {
        Connection connection = DbConnection.getConnection();

        String query = "SELECT recipe.id, recipe.name " +
                "AS recipeName, recipe.description, i.name AS ingredients, ri.\"requiredAmount\" " +
                "AS requiredAmount FROM recipe RIGHT JOIN recipe_ingredient ri on recipe.id = ri.\"idRecipe\" " +
                "LEFT JOIN ingredient i on ri.\"idIngredient\" = i.id WHERE recipe.name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //  preparedStatement.setString(1, recipe.getName());
        ResultSet resultSet = preparedStatement.executeQuery();


        Map<String, Recipe> map = new HashMap<>();

        while (resultSet.next()) {
            if (map.containsKey(resultSet.getString("recipeName"))) {
                Ingredient ingredient = new Ingredient();
                map.get(resultSet.getString("recipeName")).getIngredients().add(ingredient);
            } else {
                Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
                Ingredient ingredient = new Ingredient();
                recipe.getIngredients().add(ingredient);
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