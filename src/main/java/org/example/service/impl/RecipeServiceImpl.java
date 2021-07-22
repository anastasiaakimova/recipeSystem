package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.repository.impl.RecipeRepoImpl;
import org.example.service.RecipeService;

import java.util.Map;
import java.util.Set;

public class RecipeServiceImpl implements RecipeService {

    private RecipeRepoImpl recipeRepository = new RecipeRepoImpl();

    public RecipeServiceImpl() {
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

//    public Recipe addIngredient(Recipe recipe, List<RecipeIngredient> ingredient){
//        return recipeRepository.addIngredient(recipe, ingredient);}
//
//    public RecipeIngredient RecipeIngredient (RecipeIngredient recipeIngredient){
//        return recipeRepository.deleteIngredient(recipeIngredient); }

    public Recipe update(Recipe recipe) {
        return recipeRepository.update(recipe);
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
