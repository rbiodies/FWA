package edu.school21.cinema.repositories;

public interface CrudRepository<T> {
    void save(T entity);
}
