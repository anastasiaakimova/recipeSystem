package org.example.service.impl;

import org.example.entity.Recipe;
import org.example.repository.RecipeRepository;
import org.example.service.RecipeService;

import java.sql.SQLException;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {
    private RecipeRepository recipeRepository;

    @Override
    public Recipe save(Recipe recipe) throws SQLException {
        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe update(Recipe recipe) throws SQLException {
        return recipeRepository.update(recipe);
    }

    @Override
    public List<Recipe> getAll() throws SQLException {
        return recipeRepository.getAll();
    }

    @Override
    public Recipe getById(Integer id) throws SQLException {
        return recipeRepository.getById(id);
    }

    @Override
    public void remove(Integer id) throws SQLException {
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe findByName(String name) throws SQLException {
        return recipeRepository.getByName(name);
    }
}
