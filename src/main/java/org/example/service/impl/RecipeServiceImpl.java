package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.repository.impl.RecipeRepoImpl;
import org.example.service.RecipeService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepoImpl recipeRepository = new RecipeRepoImpl();

    public RecipeServiceImpl() {
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public RecipeIngredient deleteIngredient(RecipeIngredient recipeIngredient) {
        return recipeRepository.deleteIngredient(recipeIngredient);
    }

//    public Recipe deleteIngredient (Recipe recipe){
//        return recipeRepository.deleteIngredient(recipe); }

    public Recipe updateName(Recipe recipe) {
        return recipeRepository.updateName(recipe);
    }

    public Recipe updateDescription(Recipe recipe) {
        return recipeRepository.updateDescription(recipe);
    }

    public Recipe updateIngredients (Recipe recipe, List <RecipeIngredient> ingredients) {
        return recipeRepository.updateIngredients(recipe, ingredients);
    }

    public Map<String, Recipe> getAll() {
        return recipeRepository.getAll();
    }

    public void remove(String name) {
        recipeRepository.deleteByName(name);
    }


    public Map<String, Recipe> findByName(String name) {
        return recipeRepository.getByName(name);
    }

    @Override
    public Recipe findByIngredientsSet(Set<Ingredient> ingredient) {
        return (Recipe) recipeRepository.findRecipesByIngredients(ingredient);
    }
}
