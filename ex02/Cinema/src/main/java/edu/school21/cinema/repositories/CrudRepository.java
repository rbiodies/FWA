package edu.school21.cinema.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findById(Long id);
    List<T> findAll();
    void save(T entity);
}
