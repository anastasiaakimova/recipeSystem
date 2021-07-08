package org.example.repository;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface RecipeRepository extends GenericRepository<Recipe, Integer> {
    // get recipe by name
    Recipe getByName(String name) throws SQLException;

    // get recipe's ingredients
    Recipe viewIngredients() throws SQLException;

    // find recipes by ingredients
    List<Recipe> findRecipesByIngredients(Set<Ingredient> ingredient) throws SQLException;
}