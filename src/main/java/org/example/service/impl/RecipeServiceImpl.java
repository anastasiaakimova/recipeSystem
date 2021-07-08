package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.repository.RecipeRepository;
import org.example.repository.impl.RecipeRepoImpl;
import org.example.service.RecipeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository = new RecipeRepoImpl();

    @Override
    public Recipe save(Recipe recipe) throws SQLException {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) throws SQLException {
        return recipeRepository.update(recipe);
    }

    @Override
    public List<Recipe> getAll() throws SQLException {
        return recipeRepository.getAll();
    }

    @Override
    public Recipe getById(Integer id) throws SQLException {
        return recipeRepository.getById(id);
    }

    @Override
    public void remove(Integer id) throws SQLException {
        recipeRepository.deleteById(id);
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
