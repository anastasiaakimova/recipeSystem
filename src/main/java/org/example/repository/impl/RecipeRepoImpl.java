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

    // методы выводит имя описание всех рецептов и их ингридиенты и требуемое количество

    public Map<String, Recipe> getAll() {

        Map<String, Recipe> map = new HashMap<>();
        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT recipe.id, recipe.name " +
                    "AS \"recipeName\", recipe.description, i.name AS ingredientName, i.calories " +
                    "AS calories,  ri.\"requiredAmount\" AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri " +
                    "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i on ri.\"idIngredient\" = i.id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("List of all recipes: ");
            Recipe recipe = null;

            while (resultSet.next()) {

                if (!map.containsKey(resultSet.getString("recipeName"))) {
                    recipe = new Recipe();

                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("recipeName"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setIngredients(new ArrayList<>());
                    map.put(recipe.getName(), recipe);

                }

                //работает не корректно
                RecipeIngredient ingredient = new RecipeIngredient();

                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("ingredientName"));
                ingredient.setCalories(resultSet.getFloat("calories"));
                ingredient.setRequiredAmount(resultSet.getInt("requiredAmount"));
                recipe.getIngredients().add(ingredient);
            }

        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }

        return map;
    }

    // добавление рецепта


    public Recipe save(Recipe recipe) {
        try (Connection connection = DbConnection.getConnection()) {
            connection.setAutoCommit(false);

            String query = "INSERT INTO recipe (name, description) VALUES(?,?)";
            String query2 = "INSERT INTO recipe_ingredient (\"idRecipe\", \"idIngredient\", \"requiredAmount\") VALUES( ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());

            PreparedStatement preparedStatement1 = connection.prepareStatement(query2);

            RecipeIngredient recipeIngredient = new RecipeIngredient();

            preparedStatement1.setInt(1, recipe.getId());
            // preparedStatement1.setInt(2, recipeIngredient().getId());
            preparedStatement1.setInt(3, recipeIngredient.getRequiredAmount());

            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();

            connection.commit();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
        return recipe;
    }

    // редактироание рецепта
    // дописать апдейт ингридиентов тоже

    public Recipe update(Recipe recipe) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "UPDATE recipe SET name = ?, description = ? WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.setString(3, recipe.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return recipe;
    }

    // удаление рецепта
    public void deleteByName(String name) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "DELETE FROM recipe USING recipe_ingredient " +
                    "WHERE recipe_ingredient.\"idRecipe\" = recipe.id AND recipe.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            System.out.println("Recipe was successfully deleted! ");
        } catch (SQLException e) {
            System.out.println("Something went wrong! " + e.getMessage());
            e.printStackTrace();
        }
    }

    // метода выводит имя описание рецептов и их ингридиенты по заданному имени
    public Map<String, Recipe> getByName(String name) {
        Map<String, Recipe> map = new HashMap<>();

        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT recipe.id, recipe.name AS recipeName, recipe.description, i.name\n" +
                    "AS ingredientName, i.calories AS calories, ri.\"requiredAmount\"\n" +
                    "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri\n" +
                    "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i\n" +
                    "ON ri.\"idIngredient\" = i.id WHERE recipe.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            Recipe recipe = null;

            while (resultSet.next()) {
                if (!map.containsKey(resultSet.getString("recipeName"))) {
                    recipe = new Recipe();

                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("recipeName"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setIngredients(new ArrayList<>());
                    map.put(recipe.getName(), recipe);
                }

                //работает не корректно
                RecipeIngredient ingredient = new RecipeIngredient();

                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("ingredientName"));
                ingredient.setCalories(resultSet.getFloat("calories"));
                ingredient.setRequiredAmount(resultSet.getInt("requiredAmount"));
                recipe.getIngredients().add(ingredient);

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!" + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    //метод показывает все ингредиенты заданного рецепта по name

    public Recipe viewIngredients() throws SQLException {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT recipe.id, recipe.name " +
                    "AS recipeName, recipe.description, i.name AS ingredients, ri.\"requiredAmount\" " +
                    "AS requiredAmount FROM recipe RIGHT JOIN recipe_ingredient ri on recipe.id = ri.\"idRecipe\" " +
                    "LEFT JOIN ingredient i on ri.\"idIngredient\" = i.id WHERE recipe.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //  preparedStatement.setString(1, recipe.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            Map<String, Recipe> map = new HashMap<>();

            while (resultSet.next()) {
//                if (map.containsKey(resultSet.getString("recipeName"))) {
//                    Ingredient ingredient = new Ingredient();
//                    map.get(resultSet.getString("recipeName")).getIngredients().add(ingredient);
//                } else {
//                    Recipe recipe = new Recipe(resultSet.getInt("id"), resultSet.getString("name"));
//                    Ingredient ingredient = new Ingredient();
//                    recipe.getIngredients().add(ingredient);
//                    map.put(recipe.getName(), recipe);
//                }
            }
            //     map
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return null;
    }

    // метод показывает все возможные рецепты по заданному сету ингредиентов

    public List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) {
        try (Connection connection = DbConnection.getConnection()) {
        } catch (SQLException e) {
            System.out.println("Something went wrong!");
        }
        return null;
    }
}