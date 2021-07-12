package org.example.service.impl;

import org.example.entity.Ingredient;
import org.example.repository.IngredientRepository;
import org.example.repository.impl.IngredientRepoImpl;
import org.example.service.IngredientService;

import java.sql.SQLException;
import java.util.List;

public class IngredientServiceImpl implements IngredientService {

    private IngredientRepository ingredientRepository = new IngredientRepoImpl();

    @Override
    public Ingredient save(Ingredient ingredient) throws SQLException {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient update(Ingredient ingredient) throws SQLException {
        return ingredientRepository.update(ingredient);
    }

    @Override
    public List<Ingredient> getAll() throws SQLException {
        return ingredientRepository.getAll();
    }

    @Override
    public Ingredient getById(Integer id) throws SQLException {
        return ingredientRepository.getById(id);
    }

    @Override
    public void remove(String name) throws SQLException {
        ingredientRepository.deleteByName(name);
    }
}
