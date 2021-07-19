package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.repository.impl.RecipeRepoImpl;
import org.example.service.RecipeService;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepoImpl recipeRepository = new RecipeRepoImpl();

    public RecipeServiceImpl() throws SQLException {
    }


    public Recipe save(Recipe recipe) throws SQLException {
        return recipeRepository.save(recipe);
    }


    public Recipe update(Recipe recipe) throws SQLException {
        return recipeRepository.update(recipe);
    }


    public Map<String, Recipe> getAll() throws SQLException {
        return recipeRepository.getAll();
    }


    public Recipe getById(Integer id) throws SQLException {
        return recipeRepository.getById(id);
    }


    public void remove(String name) throws SQLException {
        recipeRepository.deleteByName(name);
    }

    @Override
    public Recipe findByName(String name) throws SQLException {
        return recipeRepository.getByName(name);
    }

    @Override
    public Recipe findByIngredientsSet(Set<Ingredient> ingredient) throws SQLException {
        return (Recipe) recipeRepository.findRecipesByIngredients(ingredient);
    }
}
