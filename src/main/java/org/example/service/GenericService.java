package org.example.service;

import java.sql.SQLException;
import java.util.List;

public interface GenericService <T, ID>{

    T save(T t) throws SQLException;

    T update(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(ID id) throws SQLException;

    void remove(String name) throws SQLException;
}
