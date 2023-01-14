package edu.school21.cinema.repositories;

import java.util.List;

public interface CrudRepository<T> {
    void save(T entity);
}
