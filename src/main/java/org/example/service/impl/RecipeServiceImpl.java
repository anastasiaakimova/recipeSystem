package org.example.service.impl;

import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.repository.impl.RecipeRepoImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The class contains of business logic.
 * Filters by ingredients list.
 * Sort by parameters.
 *
 * @author Anastasia Akimova
 * @version 1.0
 */
public class RecipeServiceImpl {

    private RecipeRepoImpl recipeRepository = new RecipeRepoImpl();

    public RecipeServiceImpl() {
    }

    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public void deleteIngredient(RecipeIngredient recipeIngredient) {
        recipeRepository.deleteIngredient(recipeIngredient);
    }

    public Recipe updateName(Recipe recipe) {
        return recipeRepository.updateName(recipe);
    }

    public Recipe updateDescription(Recipe recipe) {
        return recipeRepository.updateDescription(recipe);
    }

    public Recipe updateIngredients(Recipe recipe, List<RecipeIngredient> ingredients) {
        return recipeRepository.addIngredients(recipe, ingredients);
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

    /**
     * The method find recipe by ingredient's list and required amount.
     *
     * @param ingredients the list of ingredients by which the recipe is searched
     * @return List of recipes which was found.
     */
    public List<Recipe> findByIngredientsSet(List<RecipeIngredient> ingredients) {
        List<Recipe> recipes = recipeRepository.getAll();

        return recipes.stream()
                .filter(Objects::nonNull)
                .filter(recipe -> {
                            for (RecipeIngredient ingredient : recipe.getIngredients()) {
                                for (RecipeIngredient ingredient1 : ingredients) {
                                    if (ingredient.getName() == null) {
                                        return false;
                                    } else if (!(ingredient.getName().equals(ingredient1.getName())
                                            && ingredient.getRequiredAmount() <= ingredient1.getRequiredAmount())) {
                                        return false;
                                    }
                                    if (recipe.getIngredients().size() == ingredients.size()) {
                                        return true;
                                    }
                                    return false;
                                }
                                return true;
                            }
                            return true;
                        }
                )
                .collect(Collectors.toList());
    }

    /**
     * The method sort recipes by calories.
     *
     * @return list of recipes sorted by calories.
     */
    public List<Recipe> sortByCalories() {
        List<Recipe> recipes = recipeRepository.getAll();
        return recipes.stream().sorted(Comparator.comparing(recipe -> recipe.getRecipeCalories()))
                .collect(Collectors.toList());
    }

    /**
     * The method sort recipes by range of calories.
     *
     * @param min This is lower calorie limit.
     * @param max This is upper calorie limit.
     * @return List of recipes in range from min calories to max calories.
     */
    public List<Recipe> sortByRangeCalories(Double min, Double max) {
        List<Recipe> recipes = recipeRepository.getAll();

        return recipes.stream().filter(recipe -> {
                    if (recipe.getRecipeCalories() <= min || recipe.getRecipeCalories() >= max)
                        return false;
                    return true;
                }).sorted(Comparator.comparing(recipe -> recipe.getRecipeCalories()))
                .collect(Collectors.toList());

    }
}
