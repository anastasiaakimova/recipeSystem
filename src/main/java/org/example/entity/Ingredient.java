package org.example.entity;

/**
 * The Ingredient is a model of db's ingredient
 *
 * @author Anastasia Akimova
 * @version 1.0
 */
public class Ingredient {
    private Integer id;
    private String name;
    private Double calories;

    public Ingredient() {
    }

    public Ingredient(Integer id, String name, Double calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
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

    @Override
    public String toString() {
        return "\n" + " name: " + name +
                " calories: " + calories;
    }
}
