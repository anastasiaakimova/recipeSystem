package org.example.repository;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RecipeRepository{
    // get recipe by name
    Map<String, Recipe> getByName(String name) throws SQLException;

    // get recipe's ingredients
    Recipe viewIngredients() throws SQLException;

    // find recipes by ingredients
    List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) throws SQLException;
}