package org.example.service.impl;

import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.repository.impl.IngredientRepoImpl;
import org.example.repository.impl.RecipeRepoImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecipeServiceImpl {

    private RecipeRepoImpl recipeRepository = new RecipeRepoImpl();

    private IngredientRepoImpl ingredientRepo = new IngredientRepoImpl();

    public RecipeServiceImpl() {
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public RecipeIngredient deleteIngredient(RecipeIngredient recipeIngredient) {
        return recipeRepository.deleteIngredient(recipeIngredient);
    }

    public Recipe updateName(Recipe recipe) {
        return recipeRepository.updateName(recipe);
    }

    public Recipe updateDescription(Recipe recipe) {
        return recipeRepository.updateDescription(recipe);
    }

    public Recipe updateIngredients(Recipe recipe, List<RecipeIngredient> ingredients) {
        return recipeRepository.updateIngredients(recipe, ingredients);
    }

    public List<Recipe> getAll() {
        return recipeRepository.getAll();
    }

    public void remove(String name) {
        recipeRepository.deleteByName(name);
    }

    public Recipe findByName(String name) {
        return recipeRepository.getByName(name);
    }

    // найти рецепты по заданному списку и количеству ингредиентов
    public List<Recipe> findByIngredientsSet(List<RecipeIngredient> ingredients) {

        List<Recipe> recipes = recipeRepository.getAll();


        return recipes.stream()
                .filter(Objects::nonNull)
                .filter(recipe -> !recipe.getIngredients().isEmpty())
                .filter(recipe -> {
                    if (ingredients.equals(recipe.getIngredients()))
                        return true;
                    return false;
                })

                        /*{
                    if (ingredients.stream().equals(recipe.getIngredients().stream().filter(ingredient -> ingredients.contains(ingredient)))) //recipe.getIngredients().stream().anyMatch(ingredient -> ingredients.contains(ingredient))
                        return true;
                    return true;
                }*/
                        //////////////////////////////

        // recipe.getIngredients().stream().filter(ingredient -> ingredients.contains(ingredient))
                        /*{
                    if (!recipe.getIngredients().stream()){
                        return true;
                    }
                    return true;
                }*/


                .collect(Collectors.toList());

    }


    //  отсортировать рецепты по калорийности
    public List<Recipe> sortByCalories() {
        List<Recipe> recipes = recipeRepository.getAll();
        return recipes.stream().sorted(Comparator.comparing(recipe -> recipe.getRecipeCalories()))
                .collect(Collectors.toList());

    }

    //  вывести рецепты по калорийности в заданном диапазоне (min, max)
    public List<Recipe> sortByRangeCalories(Double min, Double max) {
        List<Recipe> recipes = recipeRepository.getAll();

        return recipes.stream().filter(recipe -> {
            if (recipe.getRecipeCalories() <= min || recipe.getRecipeCalories() >= max)
                return false;
            return true;
        }).collect(Collectors.toList());
    }
}
