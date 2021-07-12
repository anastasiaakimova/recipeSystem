package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {

    T save(T t) throws SQLException;

    T update(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(ID id) throws SQLException;

    void deleteByName(String name) throws SQLException;
}