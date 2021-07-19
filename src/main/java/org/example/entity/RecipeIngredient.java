package org.example.entity;

public class RecipeIngredient extends Ingredient {
    private Integer id;
    private String name;
    private Float calories;
    private Integer requiredAmount;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Integer id, String name, Float calories, Integer requiredAmount) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.requiredAmount = requiredAmount;
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

    public float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(Integer requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    @Override
    public String toString() {
        return "name: " + name +
                ", calories: " + calories +
                ", requiredAmount: " + requiredAmount;
    }
}
