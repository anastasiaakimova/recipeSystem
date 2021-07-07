package org.example.service;

import org.example.entity.Recipe;

import java.sql.SQLException;

public interface RecipeService extends GenericService <Recipe, Integer>{
    Recipe findByName(String name) throws SQLException;
}
