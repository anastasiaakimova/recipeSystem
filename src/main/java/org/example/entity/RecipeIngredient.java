package org.example.entity;

public class RecipeIngredient {
    private int recipeId;
    private int ingredientId;
    private int requiredAmount;

    public RecipeIngredient() {
    }

    public RecipeIngredient(int recipeId, int ingredientId, int requiredAmount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.requiredAmount = requiredAmount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
