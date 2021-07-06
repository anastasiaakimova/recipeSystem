package org.example.entity;

import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private float calories;


    public Ingredient() {
    }

    public Ingredient(int id, String name, float calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
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

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

}
