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
            String query = "INSERT INTO recipe (name, description) VALUES(?,?)";
            String query2 = "INSERT INTO recipe_ingredient (\"idRecipe\", \"idIngredient\", \"requiredAmount\") VALUES( ?, ?, ?)";
            String query3 = "SELECT id FROM recipe WHERE name = ?";
            String query4 = "SELECT id FROM ingredient WHERE name = ?";

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

            PreparedStatement pSt3 = connection.prepareStatement(query4);
            PreparedStatement pSt1 = connection.prepareStatement(query2);

            List<RecipeIngredient> ingredients = recipe.getIngredients();

            for (RecipeIngredient ingredient : ingredients) {

//                pSt3.setString(1, ingredient.getName());
//                ResultSet resultSet1 = pSt3.executeQuery();
//                while (resultSet1.next()) {
//                    ingredient.setId(resultSet1.getInt("id"));
//                }

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

    // редактироание названия рецепта
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

    // редактироание описания рецепта
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


    //редактирование ингридиентов рецепта
    public Recipe updateIngredients(Recipe recipe, List<RecipeIngredient> ingredients) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "UPDATE recipe_ingredient SET  \"idIngredient\" =?,  \"requiredAmount\" = ? WHERE \"idRecipe\" = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
           // List<RecipeIngredient> ingredients = recipe.getIngredients();
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

    // удаление ингридиента из рецепта
    public RecipeIngredient deleteIngredient(RecipeIngredient recipeIngredient) {
        try (Connection connection = DbConnection.getConnection()) {
            String query = "DELETE FROM recipe_ingredient WHERE \"idIngredient\" = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, recipeIngredient.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException | NullPointerException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
        return recipeIngredient;
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
                RecipeIngredient ingredient = new RecipeIngredient();
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("ingredientName"));
                ingredient.setCalories(resultSet.getFloat("calories"));
                ingredient.setRequiredAmount(resultSet.getInt("requiredAmount"));
                recipe.getIngredients().add(ingredient);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong!" + e.getMessage());
        }
        return map;
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