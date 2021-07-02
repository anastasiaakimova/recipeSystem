package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> findAll() throws SQLException;

    T findById(ID id) throws SQLException;
}