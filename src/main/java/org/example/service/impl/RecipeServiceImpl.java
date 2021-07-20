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

    public RecipeServiceImpl() {
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }


    public Recipe update(Recipe recipe) {
        return recipeRepository.update(recipe);
    }


    public Map<String, Recipe> getAll() {
        return recipeRepository.getAll();
    }

    public void remove(String name) {
        recipeRepository.deleteByName(name);
    }

    @Override
    public Recipe findByName(String name) {
        return recipeRepository.getByName(name);
    }

    @Override
    public Recipe findByIngredientsSet(Set<Ingredient> ingredient) {
        return (Recipe) recipeRepository.findRecipesByIngredients(ingredient);
    }
}
