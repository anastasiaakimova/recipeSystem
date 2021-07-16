package org.example.repository.impl;

import org.example.entity.RecipeIngredient;
import org.example.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RecipeIngredientRepoImpl {
//    public RecipeIngredient save(RecipeIngredient recipeIngredient) throws SQLException {
//        Connection connection = DbConnection.getConnection();
//        connection.setAutoCommit(false);
//        String query = "INSERT INTO recipe (name, description) VALUES(?,?)";
//        String query2 = "INSERT INTO recipe_ingredient (\"idRecipe\", \"idIngredient\", \"requiredAmount\") VALUES( ?, ?, ?)";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setString(1, recipeIngredient.getRecipe().getName());
//        preparedStatement.setString(2, recipeIngredient.getRecipe().getDescription());
//
//        PreparedStatement preparedStatement1 = connection.prepareStatement(query2);
//
//        //проверка на существование ингредиента
//
//        if (recipeIngredient.getIngredient() == null) {
//
//            String query3 = "INSERT INTO ingredient (name, calories) VALUES (?, ?)";
//            PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
//            preparedStatement2.setString(1, recipeIngredient.getIngredient().getName());
//            preparedStatement2.setFloat(2, recipeIngredient.getIngredient().getCalories());
//            preparedStatement2.executeUpdate();
//            preparedStatement2.close();
//
//        } else
//
//            preparedStatement1.setInt(1, recipeIngredient.getRecipe().getId());
//        preparedStatement1.setInt(2, recipeIngredient.getIngredient().getId());
//        preparedStatement1.setInt(3, recipeIngredient.getRequiredAmount());
//
//        preparedStatement.executeUpdate();
//        preparedStatement1.executeUpdate();
//
//        connection.commit();
//        preparedStatement.close();
//        preparedStatement1.close();
//
//        connection.close();
//
//        return recipeIngredient;
//    }
//
//    public RecipeIngredient update(RecipeIngredient recipeIngredient) throws SQLException {
//        return null;
//    }
//
//    public List<RecipeIngredient> getAll() throws SQLException {
//        return null;
//    }
//
//    public RecipeIngredient getById(Integer integer) throws SQLException {
//        return null;
//    }
//
//    public void deleteByName(String name) throws SQLException {
//
//    }
}
