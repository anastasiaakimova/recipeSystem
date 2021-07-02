package org.example.repository;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;

import java.sql.SQLException;
import java.util.List;

public interface RecipeRepository extends GenericRepository<Recipe, Integer> {
    Recipe findByName(Recipe name) throws SQLException;

    List<Recipe> viewIngredients(List<Ingredient> ingredient) throws SQLException;
}