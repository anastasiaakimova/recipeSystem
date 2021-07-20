package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.repository.impl.IngredientRepoImpl;

import java.sql.SQLException;
import java.util.List;

public class IngredientServiceImpl {

    private IngredientRepoImpl ingredientRepository = new IngredientRepoImpl();

    public Ingredient save(Ingredient ingredient) throws SQLException {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.update(ingredient);
    }

    public List<Ingredient> getAll() {
        return ingredientRepository.getAll();
    }

    public void remove(String name) {
        ingredientRepository.deleteByName(name);
    }

    public Ingredient getByName(String name) {
        return ingredientRepository.getByName(name);
    }
}
