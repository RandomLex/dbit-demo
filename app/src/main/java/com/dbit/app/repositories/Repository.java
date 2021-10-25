package com.dbit.app.repositories;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    List<T> findAll();
    Optional<T> find(int id);
    T save(T entity);
    Optional<T> remove(T entity);
}
