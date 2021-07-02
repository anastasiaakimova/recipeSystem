package org.example.entity;

import java.util.List;

public class Recipe {

    private int id;
    private String name;
    private String description;

    private List<RecipeIngredients> recipeIngredients;

    public Recipe() {
    }

    public Recipe(int id, String name, String description, List<RecipeIngredients> recipeIngredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<RecipeIngredients> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(List<RecipeIngredients> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
