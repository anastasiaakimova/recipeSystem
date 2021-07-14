package org.example.service;

import org.example.entity.Ingredient;

import java.sql.SQLException;

public interface IngredientService extends GenericService <Ingredient, Integer>{

    Ingredient getByName(String name) throws SQLException;

}