package org.example.repository.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
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

    // методы выводит имя описание всех рецептов и их ингридиенты
    @Override
    public List<Recipe> getAll() throws SQLException {
        List<Recipe> recipes = new ArrayList<Recipe>();
        String query = "SELECT recipe.id, recipe.name " +
                "AS recipeName, recipe.description, i.name AS ingredients, ri.requireamount " +
                "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredients ri " +
                "ON recipe.id = ri.id_recipe LEFT JOIN ingredients i on ri.id_ingredients = i.id";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), (List<RecipeIngredient>) resultSet.getArray("ingredients"));
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
        String query = "INSERT INTO recipe (id, name, description) VALUES(?,?,?))";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, recipe.getId());
        preparedStatement.setString(2, recipe.getName());
        preparedStatement.setString(3, recipe.getDescription());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return recipe;
    }

    // редактироание рецепта
    // дописать апдейт ингридиентов тоже
    @Override
    public Recipe update(Recipe recipe) throws SQLException {
        String query = "UPDATE recipe SET name = ?, description = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, recipe.getName());
        preparedStatement.setString(2, recipe.getDescription());
        preparedStatement.setInt(3, recipe.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return recipe;
    }

    // метод выводит имя описание рецептов и их ингридиенты по заданному id
    @Override
    public Recipe getById(Integer id) throws SQLException {
        String query = "SELECT recipe.id, recipe.name AS recipeName, recipe.description, i.name AS ingredients, ri.requireamount AS requiredAmount FROM recipe LEFT JOIN recipe_ingredients ri ON recipe.id = ri.id_recipe LEFT JOIN ingredients i on ri.id_ingredients = i.id WHERE recipe.id = ?";
        Recipe recipe = new Recipe();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Recipe newRecipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), (List<RecipeIngredient>) resultSet.getArray("ingredients"));
            newRecipe.setId(id);
            recipe= newRecipe;
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
        preparedStatement.close();
        connection.close();
    }

    // метода выводит все поля рецепта по заданному имени
    // дописать
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
        preparedStatement.close();
        connection.close();
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
        preparedStatement.close();
        connection.close();

        return null;
    }

    // метод показывает все возможные рецепты по заданному сету ингредиентов
    @Override
    public List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) throws SQLException {
        return null;
    }
}