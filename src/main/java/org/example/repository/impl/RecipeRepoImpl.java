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

    public List<Recipe> getAll() {
        Connection connection = DbConnection.getConnection();
        List<Recipe> recipes = new ArrayList<>();
        String query = "SELECT recipe.id, recipe.name AS \"recipeName\", recipe.description, i.name " +
                "AS ingredients, ri.\"requiredAmount\" " +
                "AS \"requiredAmount\" FROM recipe LEFT JOIN recipe_ingredient ri " +
                "ON recipe.id = ri.\"idRecipe\" " +
                "LEFT JOIN ingredient i on ri.\"idIngredient\" = i.id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return recipes;
    }

    // добавление рецепта
    // переписать класс recipeIngredient??? и переписать этот метод под него

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

    public Recipe update(Recipe recipe) {
        Connection connection = DbConnection.getConnection();
        String query = "UPDATE recipe SET name = ?, description = ? WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setString(3, recipe.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return recipe;
    }

    // метод выводит имя описание рецептов и их ингридиенты по заданному id

    public Recipe getById(Integer id) {
        Connection connection = DbConnection.getConnection();
        String query = "SELECT recipe.id, recipe.name  AS recipeName, recipe.description, i.name  AS ingredients, ri.\"requiredAmount\"\n" +
                "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri\n" +
                "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i\n" +
                "ON ri.\"idIngredient\" = i.id WHERE recipe.id = ?";
        Recipe recipe = new Recipe();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Recipe newRecipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description"), Collections.EMPTY_LIST);
                newRecipe.setId(id);
                recipe = newRecipe;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return recipe;
    }

    // удаление рецепта

    public void deleteByName(String name) {
        Connection connection = DbConnection.getConnection();
        String query = "DELETE * FROM recipe WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
    }

    // метода выводит имя описание рецептов и их ингридиенты по заданному имени

    public Recipe getByName(String name){
        Connection connection = DbConnection.getConnection();
        String query = "SELECT recipe.id, recipe.name AS recipeName, recipe.description, i.name\n" +
                "AS ingredient, ri.\"requiredAmount\"\n" +
                "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri\n" +
                "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i\n" +
                "ON ri.\"idIngredient\" = i.id WHERE recipe.name = ?";
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("recipeName"), resultSet.getString("description"), Collections.EMPTY_LIST);
                recipes.add(recipe);
                //    Map<String, Recipe> map = new HashMap<>();
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!");

        }
        return (Recipe) recipes;
    }

    //метод показывает все ингредиенты заданного рецепта по name

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

    public List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) throws SQLException {
        Connection connection = DbConnection.getConnection();

        return null;
    }
}