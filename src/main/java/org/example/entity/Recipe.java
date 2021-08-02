package org.example.entity;

import java.util.List;

/**
 * The Recipe is a model of db's recipe
 *
 * @author Anastasia Akimova
 * @version 1.0
 */

public class Recipe {

    private Integer id;
    private String name;
    private String description;
    private List<RecipeIngredient> ingredients;

    public Recipe() {
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

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Count calories in recipe.
     *
     * @return sum of calories in every ingredient which consists of recipe in 100 grams
     */
    public Double getRecipeCalories() {
        return getIngredients().stream().map(i -> i.getCalories() / 100 * i.getRequiredAmount()).reduce(0., (a, b) -> a + b);
    }

    @Override
    public String toString() {
        if (ingredients.get(0).getName() == null) {
            return " name: " + name + "\n" +
                    " description: " + description + "\n" +
                    " calories doesn't count! " + "\n" +
                    " ingredients doesn't added! " + "\n";
        } else
            return
                    " name: " + name + "\n" +
                            " description: " + description + "\n" +
                            " calories: " + getRecipeCalories() + " Kcal" + "\n" +
                            " ingredients: " + ingredients + "\n";
    }
}
