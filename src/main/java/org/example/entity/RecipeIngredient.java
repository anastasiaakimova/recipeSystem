package org.example.entity;

public class RecipeIngredient {
    private Integer recipeId;
    private Integer ingredientId;
    private Integer requiredAmount;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Integer recipeId, Integer ingredientId, Integer requiredAmount) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.requiredAmount = requiredAmount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Integer requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
