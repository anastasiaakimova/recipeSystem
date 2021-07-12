package org.example.entity;

import java.util.List;

public class Recipe {

    private Integer id;
    private String name;
    private String description;

    private List<RecipeIngredient> recipeIngredients;

    public Recipe() {
    }

    public Recipe(Integer id, String name, String description, List<RecipeIngredient> recipeIngredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    @Override
    public String toString() {
        return "name= " + name +
                "description= " + description +
                "recipeIngredients= " + recipeIngredients;
    }
}
