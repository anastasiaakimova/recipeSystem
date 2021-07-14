package org.example.entity;

public class RecipeIngredient {
    private Recipe recipe;
    private Ingredient ingredient;
    private Integer requiredAmount;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Integer requiredAmount) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.requiredAmount = requiredAmount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Integer requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
