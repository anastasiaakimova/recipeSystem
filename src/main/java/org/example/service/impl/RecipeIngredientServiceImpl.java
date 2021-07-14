package org.example.service.impl;

import org.example.entity.RecipeIngredient;
import org.example.repository.GenericRepository;
import org.example.repository.impl.RecipeIngredientRepoImpl;
import org.example.service.RecipeIngredientService;

import java.sql.SQLException;
import java.util.List;

public class RecipeIngredientServiceImpl implements RecipeIngredientService {
    private GenericRepository genericRepository = new RecipeIngredientRepoImpl();

    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) throws SQLException {
        return (RecipeIngredient) genericRepository.save(recipeIngredient);
    }

    @Override
    public RecipeIngredient update(RecipeIngredient recipeIngredient) throws SQLException {
        return (RecipeIngredient) genericRepository.update(recipeIngredient);
    }

    @Override
    public List<RecipeIngredient> getAll() throws SQLException {
        return genericRepository.getAll();
    }

    @Override
    public RecipeIngredient getById(Integer id) throws SQLException {
        return (RecipeIngredient) genericRepository.getById(id);
    }

    @Override
    public void remove(String name) throws SQLException {
        genericRepository.deleteByName(name);
    }
}
