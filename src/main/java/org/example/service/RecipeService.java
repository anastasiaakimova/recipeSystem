package org.example.service;

import org.example.entity.Ingredient;
import org.example.entity.Recipe;

import java.sql.SQLException;
import java.util.Set;

public interface RecipeService{
    Recipe findByName(String name) throws SQLException;
    Recipe findByIngredientsSet(Set<Ingredient> ingredient) throws  SQLException;
}