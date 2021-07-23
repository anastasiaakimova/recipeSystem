package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;
import org.example.entity.RecipeIngredient;
import org.example.repository.impl.IngredientRepoImpl;
import org.example.repository.impl.RecipeRepoImpl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RecipeServiceImpl{

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

//    public Recipe deleteIngredient (Recipe recipe){
//        return recipeRepository.deleteIngredient(recipe); }

    public Recipe updateName(Recipe recipe) {
        return recipeRepository.updateName(recipe);
    }

    public Recipe updateDescription(Recipe recipe) {
        return recipeRepository.updateDescription(recipe);
    }

    public Recipe updateIngredients(Recipe recipe, List<RecipeIngredient> ingredients) {
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



    ////////////////////////////////////////////////////////////////////////
    public List<Map <String, Recipe>> findByIngredientsSet(Recipe recipe, List <RecipeIngredient> ingredients) {
        Stream<RecipeIngredient> stream = ingredients.stream();
        if (recipe != null){
            stream = stream.filter(recipeIngredient -> recipe.equals(recipeIngredient.getName()));

        }
        return stream.map(ingredients -> {
            Map<String, Recipe> map = new LinkedHashMap<>();
            map.put();
            return map;

        }).collect(Collectors.toList());

//        RecipeIngredient ingredient = null;
//
//        List<Recipe> recipes = new ArrayList<>();
//
//
//
//
//        recipes.stream()
//                .filter(Objects::nonNull)
//                .filter(recipe -> !recipe.getName().isEmpty() && recipe.getIngredients().contains(ingredient.getName()) )
//                .forEach(System.out::println);
//        return recipes;
    }

    public Recipe sortByCalories() {

        return null;
    }

    public Recipe sortByMaxCalories() {
        return null;

    }

    public Recipe sortByMinCalories() {
        return null;
    }
}
