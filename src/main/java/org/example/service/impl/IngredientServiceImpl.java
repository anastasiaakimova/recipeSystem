package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.repository.impl.IngredientRepoImpl;
import org.example.service.IngredientService;

import java.sql.SQLException;
import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private IngredientRepoImpl ingredientRepository = new IngredientRepoImpl();


    public Ingredient save(Ingredient ingredient) throws SQLException {
        return ingredientRepository.save(ingredient);
    }


    public Ingredient update(Ingredient ingredient) throws SQLException {
        return ingredientRepository.update(ingredient);
    }


    public List<Ingredient> getAll() throws SQLException {
        return ingredientRepository.getAll();
    }


    public Ingredient getById(Integer id) throws SQLException {
        return ingredientRepository.getById(id);
    }


    public void remove(String name) throws SQLException {
        ingredientRepository.deleteByName(name);
    }


    public Ingredient getByName(String name) throws SQLException {
        return ingredientRepository.getByName(name);
    }
}
