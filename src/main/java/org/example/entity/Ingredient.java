package org.example.entity;

public class Ingredient {
    private Integer id;
    private String name;
    private Float calories;

    public Ingredient() {
    }

    public Ingredient(Integer id, String name, Float calories) {
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

    public float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return " name: " + name +
                " calories: " + calories;
    }
}
