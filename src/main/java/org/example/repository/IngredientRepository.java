package org.example.repository;

import org.example.entity.Ingredient;

import java.sql.SQLException;

public interface IngredientRepository{
    Ingredient getByName(String name) throws SQLException;

}
