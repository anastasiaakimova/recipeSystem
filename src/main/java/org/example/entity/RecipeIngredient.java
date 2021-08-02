package org.example.entity;

/**
 * The Object of RecipeIngredient class is ingredient which used in the recipe
 *
 * @author Anastasia Akimova
 * @version 1.0
 */
public class RecipeIngredient extends Ingredient {
    private Integer id;
    private String name;
    private Double calories;
    private Integer requiredAmount;

    public RecipeIngredient() {
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

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
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
        return "\n" + " name: " + name +
                ", calories: " + calories +
                ", required amount: " + requiredAmount;
    }
}
