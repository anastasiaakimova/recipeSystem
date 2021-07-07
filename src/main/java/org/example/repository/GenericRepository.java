package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {

    T save(T t);

    T update(T t);

    List<T> findAll() throws SQLException;

    T findById(ID id) throws SQLException;

    void deleteById(ID id);
}