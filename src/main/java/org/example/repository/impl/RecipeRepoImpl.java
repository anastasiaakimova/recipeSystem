package org.example.repository.impl;

import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class is responsible to databases requests for recipes and response processing.
 *
 * @author Anastasia Akimova
 * @version 1.0
 */
public class RecipeRepoImpl {

    /**
     * The method get recipe's name, recipe's description,
     * ingredient's name, ingredient's calories, ingredient's required amount of all recipes from database.
     *
     * @return List of recipes with their fields.
     */
    public List<Recipe> getAll() {
        Map<String, Recipe> map = new HashMap<>();
        Recipe recipe = null;
        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT recipe.id, recipe.name " +
                    "AS \"recipeName\", recipe.description, i.name " +
                    "AS ingredientName, i.calories " +
                    "AS calories,  ri.\"requiredAmount\" " +
                    "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri " +
                    "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i " +
                    "ON ri.\"idIngredient\" = i.id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (!map.containsKey(resultSet.getString("recipeName"))) {
                    recipe = new Recipe();
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("recipeName"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setIngredients(new ArrayList<>());
                    map.put(recipe.getName(), recipe);
                } else {
                    recipe = map.get(resultSet.getString("recipeName"));
                }
                RecipeIngredient ingredient = new RecipeIngredient();
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("ingredientName"));
                ingredient.setCalories(resultSet.getDouble("calories"));
                ingredient.setRequiredAmount(resultSet.getInt("requiredAmount"));
                recipe.getIngredients().add(ingredient);
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
        List<Recipe> result = new ArrayList<>(map.values());
        return result;
    }

    /**
     * The method adding new recipe to database.
     *
     * @param recipe This is object which should be added to database.
     * @return Recipe which was added to database.
     */
    public Recipe save(Recipe recipe) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "INSERT INTO recipe (name, description) VALUES(?,?)";
            String query2 = "INSERT INTO recipe_ingredient (\"idRecipe\", \"idIngredient\", \"requiredAmount\") VALUES( ?, ?, ?)";
            String query3 = "SELECT id FROM recipe WHERE name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getDescription());
            preparedStatement.executeUpdate();

            PreparedStatement pSt2 = connection.prepareStatement(query3);
            pSt2.setString(1, recipe.getName());
            ResultSet resultSet = pSt2.executeQuery();
            while (resultSet.next()) {
                recipe.setId(resultSet.getInt("id"));
            }
            PreparedStatement pSt1 = connection.prepareStatement(query2);
            List<RecipeIngredient> ingredients = recipe.getIngredients();
            for (RecipeIngredient ingredient : ingredients) {
                connection.setAutoCommit(false);
                pSt1.setInt(1, recipe.getId());
                pSt1.setInt(2, ingredient.getId());
                pSt1.setInt(3, ingredient.getRequiredAmount());
                pSt1.addBatch();
            }
            pSt1.executeBatch();
            connection.commit();

        } catch (SQLException | NullPointerException e) {
            if (e.getMessage().contains("name_unique")) {
                System.out.println("This recipe already exists! ");
            } else {
                System.out.println("Something went wrong! " + e.getMessage());
            }
        }
        System.out.println("Your recipe was successfully added! ");
        return recipe;
    }

    /**
     * The method edit recipe's name.
     *
     * @param recipe which name should be edited.
     * @return Recipe with new name.
     */
    public Recipe updateName(Recipe recipe) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "UPDATE recipe SET name = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setInt(2, recipe.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        return recipe;
    }

    /**
     * The method edit recipe's description.
     *
     * @param recipe which description should be edited.
     * @return Recipe with new description.
     */
    public Recipe updateDescription(Recipe recipe) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "UPDATE recipe SET description = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, recipe.getDescription());
            preparedStatement.setInt(2, recipe.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        return recipe;
    }

    /**
     * The method add ingredients and required amount of them to recipe.
     *
     * @param recipe      which should have new ingredients.
     * @param ingredients which should be added.
     * @return Recipe with new ingredients and required amount for them.
     */
    public Recipe addIngredients(Recipe recipe, List<RecipeIngredient> ingredients) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "INSERT INTO recipe_ingredient  (\"idIngredient\", \"requiredAmount\", \"idRecipe\") VALUES (?, ?, ? )";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            for (RecipeIngredient ingredient : ingredients) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, ingredient.getId());
                preparedStatement.setInt(2, ingredient.getRequiredAmount());
                preparedStatement.setInt(3, recipe.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        return recipe;
    }

    /**
     * The method delete ingredients and required amount of them to recipe.
     *
     * @param recipeIngredient this ingredient which should be deleted from recipe.
     */
    public void deleteIngredient(RecipeIngredient recipeIngredient) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "DELETE FROM recipe_ingredient WHERE \"idIngredient\" = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, recipeIngredient.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
    }

    /**
     * The method delete recipe from database.
     *
     * @param name of recipe which should be deleted.
     */
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
        }
    }

    /**
     * The method get recipe's name, recipe's description recipe's ingredients required amount of ingredients by recipe's name.
     *
     * @param name of recipe which should be found.
     * @return Recipe which was found by name.
     */
    public Recipe getByName(String name) {
        Recipe recipe = null;
        try (Connection connection = DbConnection.getConnection()) {
            String query = "SELECT recipe.id, recipe.name " +
                    "AS recipeName, recipe.description, i.name\n" +
                    "AS ingredientName, i.calories " +
                    "AS calories, ri.\"requiredAmount\"\n" +
                    "AS requiredAmount FROM recipe LEFT JOIN recipe_ingredient ri\n" +
                    "ON recipe.id = ri.\"idRecipe\" LEFT JOIN ingredient i\n" +
                    "ON ri.\"idIngredient\" = i.id WHERE recipe.name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            recipe = new Recipe();
            while (resultSet.next()) {
                if (recipe.getName() == null) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("recipeName"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setIngredients(new ArrayList<>());
                }
                RecipeIngredient ingredient = new RecipeIngredient();
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("ingredientName"));
                ingredient.setCalories(resultSet.getDouble("calories"));
                ingredient.setRequiredAmount(resultSet.getInt("requiredAmount"));
                recipe.getIngredients().add(ingredient);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        return recipe;
    }

}