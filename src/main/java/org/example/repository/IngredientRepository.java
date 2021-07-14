package org.example.repository;

import org.example.entity.Ingredient;

import java.sql.SQLException;

public interface IngredientRepository extends GenericRepository <Ingredient, Integer>{
    Ingredient getByName(String name) throws SQLException;

}
