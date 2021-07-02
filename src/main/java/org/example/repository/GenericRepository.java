package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface GenericRepository<T, ID> {
    // get all
    List<T> findAll() throws SQLException;

    // get by id
    T findById(ID id) throws SQLException;
}